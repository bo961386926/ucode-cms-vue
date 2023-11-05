package xin.altitude.cms.take.time.dao;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import xin.altitude.cms.take.time.domain.JobRecord;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 塞泰先生
 * @since 2023/10/10 19:04
 **/
public class JobRecordMapper {
    public static final String SQL = "insert into tb_job_record(id,job_name,start_time,end_time,duration,extra) values(?,?,?,?,?,?);";
    private final Logger logger = LoggerFactory.getLogger(JobRecordMapper.class);

    /**
     * 保存单条Job执行记录
     *
     * @param dataSource
     * @param record
     * @return
     */
    public static int saveJobRecord(DataSource dataSource, JobRecord record) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, record.getId());
            preparedStatement.setString(2, record.getJobName());
            preparedStatement.setString(3, record.getStartTime());
            preparedStatement.setString(4, record.getEndTime());
            preparedStatement.setString(5, record.getDuration());
            preparedStatement.setString(6, record.getExtra());
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
