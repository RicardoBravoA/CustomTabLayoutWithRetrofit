package pe.com.orbis.tablayout.model.response;

import java.util.List;

/**
 * Created by Ricardo Bravo on 4/05/16.
 */

public class PlaceResponse {

    /**
     * status : success
     * code : 200
     */

    private MetaBean _meta;
    /**
     * placeid : 1
     * description : Place 1
     */

    private List<DataBean> data;

    public MetaBean get_meta() {
        return _meta;
    }

    public void set_meta(MetaBean _meta) {
        this._meta = _meta;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class MetaBean {
        private String status;
        private String code;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class DataBean {
        private int placeid;
        private String description;

        public int getPlaceid() {
            return placeid;
        }

        public void setPlaceid(int placeid) {
            this.placeid = placeid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
