package design.designbulider;

public class ConfigBuilder {
    private String sql;//非必须
    private String type;//必须
    private String param;//非必须

    public static class Builder {
        ConfigBuilder configBuilder = new ConfigBuilder();
        public Builder(String type) {
            configBuilder.setType(type);
        }

        public Builder setSql(String sql){
            configBuilder.setSql(sql);
            return this;
        }

        public Builder setParam(String param){
            configBuilder.setParam(param);
            return this;
        }
        public ConfigBuilder build(){
            return configBuilder;
        }
    }

    public Builder getBuilder(String type){
        return  new Builder(type);
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
