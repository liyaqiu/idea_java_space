package design.designfactory;

import design.designbulider.Config;

public abstract class ConfigFactory {
    public abstract Config getWindowConfig();
    public abstract Config getLinuxConfig();
}
