package design.designbulider;

abstract class DBBuilder {
    public abstract void setSql();
    public abstract void setParam1();
    public abstract void setParam2();
    public abstract void setDBType();
    public abstract Config getConfig();
}
