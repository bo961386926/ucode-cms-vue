package xin.altitude.cms.framework.core.page;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author ucode
 */
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消息状态码
     */
    private int code;
    /**
     * 消息内容
     */
    private String msg;
    
    private DataBody data;
    
    /**
     * 表格数据对象
     */
    public TableDataInfo() {
    }
    
    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, int total) {
        // this.rows = list;
        // this.total = total;
        this.data = new DataBody(list, total);
    }
    
    // public long getTotal() {
    //     return total;
    // }
    //
    // public void setTotal(long total) {
    //     this.total = total;
    // }
    //
    // public List<?> getRows() {
    //     return rows;
    // }
    //
    // public void setRows(List<?> rows) {
    //     this.rows = rows;
    // }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public DataBody getData() {
        return data;
    }
    
    public void setData(DataBody data) {
        this.data = data;
    }
    
    public static class DataBody {
        /**
         * 总记录数
         */
        private long total;
        
        /**
         * 列表数据
         */
        private List<?> records;
        
        public DataBody(List<?> records, long total) {
            this.total = total;
            this.records = records;
        }
        
        public long getTotal() {
            return total;
        }
        
        public void setTotal(long total) {
            this.total = total;
        }
        
        public List<?> getRecords() {
            return records;
        }
        
        public void setRecords(List<?> records) {
            this.records = records;
        }
    }
}
