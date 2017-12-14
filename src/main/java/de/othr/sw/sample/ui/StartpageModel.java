package de.othr.sw.sample.ui;

import de.othr.sw.sample.entity.TestEntity;
import de.othr.sw.sample.services.TestService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class StartpageModel implements Serializable {

    @Inject
    private TestService testService;

    private String stringProperty;
    private int intProperty;

    public String getStringProperty() {
        return stringProperty;
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
    }

    public int getIntProperty() {
        return intProperty;
    }

    public void setIntProperty(int intProperty) {
        this.intProperty = intProperty;
    }

    public void createEntity() {
        TestEntity entity = new TestEntity();
        entity.setStringProperty(this.stringProperty);
        entity.setIntegerProperty(this.intProperty);

        entity = testService.createEntity(entity);
    }

    public List<TestEntity> getEntities() {
        return this.testService.listEntitites();
    }
}
