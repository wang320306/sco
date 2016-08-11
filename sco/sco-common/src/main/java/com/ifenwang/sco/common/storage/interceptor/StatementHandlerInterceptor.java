package com.ifenwang.sco.common.storage.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import com.ifenwang.sco.common.utils.ReflectUtils;

/**  
 * MyBatis SQL语句分页拦截器.
 * 分页拦截器，用于拦截需要进行分页查询的操作，然后对其进行分页处理。  
 * 利用拦截器实现Mybatis分页的原理：  
 * 要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象，Mybatis在执行Sql语句前就会产生一个包含Sql语句的Statement对象，而且对应的Sql语句  
 * 是在Statement之前产生的，所以可以在它生成Statement之前对用来生成Statement的Sql语句下手。在Mybatis中Statement语句是通过RoutingStatementHandler对象的  
 * prepare方法生成的。所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句，之后再调用  
 * StatementHandler对象的prepare方法，即调用invocation.proceed()。  
 * 对于分页而言，在拦截器里面我们还需要做的一个操作就是统计满足当前条件的记录一共有多少，这是通过获取到了原始的Sql语句后，把它改为对应的统计语句再利用Mybatis封装好的参数和设  
 * 置参数的功能把Sql语句中的参数进行替换，之后再执行查询记录数的Sql语句进行总记录数的统计。  
 */
@Intercepts({ @Signature(
        type = StatementHandler.class, 
        method = "prepare", 
        args = { Connection.class }) })
public class StatementHandlerInterceptor implements Interceptor
{
    @Override
    public Object plugin(Object target)
    {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties arg0)
    {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable
    {
        /*
         * 对于StatementHandler其实只有两个实现类，一个是RoutingStatementHandler，另一个是抽象类BaseStatementHandler，    
         * BaseStatementHandler有三个子类，分别是SimpleStatementHandler，PreparedStatementHandler和CallableStatementHandler，    
         * SimpleStatementHandler是用于处理Statement的，PreparedStatementHandler是处理PreparedStatement的，而CallableStatementHandler是    
         * 处理CallableStatement的。Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，而在RoutingStatementHandler里面拥有一个    
         * StatementHandler类型的delegate属性，RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler，即SimpleStatementHandler、    
         * PreparedStatementHandler或CallableStatementHandler，在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法。    
         * 我们在PageInterceptor类上已经用@Signature标记了该Interceptor只拦截StatementHandler接口的prepare方法，又因为Mybatis只有在建立RoutingStatementHandler的时候    
         * 是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
         */

        if (invocation.getTarget() instanceof RoutingStatementHandler)
        {
            RoutingStatementHandler statement = (RoutingStatementHandler) invocation.getTarget();
            Object handlerObj = ReflectUtils.getFieldValue(statement, "delegate");
            if (!(handlerObj instanceof PreparedStatementHandler))
            {
                return invocation.proceed();
            }
            PreparedStatementHandler handler = (PreparedStatementHandler) handlerObj;
            RowBounds rowBounds = (RowBounds) ReflectUtils.getFieldValue(handler, "rowBounds");
            if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT)
            {
                BoundSql boundSql = statement.getBoundSql();
                String sql = buildForMysql(boundSql.getSql(), rowBounds.getOffset(), rowBounds.getLimit());
                ReflectUtils.setFieldValue(boundSql, "sql", sql);
            }
        }

        return invocation.proceed();
    }

    /**
     * 生成MySQL分页查询.
     * @param sql   需要分页的SQL语句
     * @param start 起始记录
     * @param size  记录总数
     * @return      构造后的分页语句
     */
    public String buildForMysql(String sql, int start, int size)
    {
        return String.format("SELECT * FROM (%s) __a LIMIT %d OFFSET %d", sql, size, start);
    }

    /**
     * 生成Oracle分页查询.
     * @param sql   需要分页的SQL语句
     * @param start 起始记录
     * @param size  记录总数
     * @return      构造后的分页语句
     */
    public String buildForOracle(String sql, int start, int size)
    {
        if (start == 1)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT x__.* FROM (");
            sb.append(sql);
            sb.append(") x__ WHERE ROWNUM < ").append(start + size);
            return sb.toString();
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT y__.* FROM (");
            sb.append("SELECT x__.*, ROWNUM rn__ FROM (");
            sb.append(sql);
            sb.append(") x__ WHERE ROWNUM < ").append(start + size);
            sb.append(") y__ WHERE rn__ >= ").append(start);
            return sb.toString();
        }
    }
}
