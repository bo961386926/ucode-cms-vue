package xin.altitude.demo.jpa.ddl.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

public class CommentIntegrator implements Integrator {
    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        processComment(metadata);
    }

    /**
     * Not used.
     */
    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactoryImplementor, SessionFactoryServiceRegistry sessionFactoryServiceRegistry) {
    }

    /**
     * 生成注释代码
     */
    protected void processComment(Metadata metadata) {
        for (PersistentClass persistentClass : metadata.getEntityBindings()) {
            Class<?> clz = persistentClass.getMappedClass();
            if (clz.isAnnotationPresent(ApiModel.class)) {
                ApiModel apiModel = clz.getAnnotation(ApiModel.class);
                persistentClass.getTable().setComment(apiModel.description());
            }
            Property identifierProperty = persistentClass.getIdentifierProperty();
            if (identifierProperty != null) {
                propertyComment(persistentClass, identifierProperty.getName());
            } else {
                org.hibernate.mapping.Component component = persistentClass.getIdentifierMapper();
                if (component != null) {
                    Iterator<Property> iterator = component.getPropertyIterator();
                    while (iterator.hasNext()) {
                        propertyComment(persistentClass, iterator.next().getName());
                    }
                }
            }
            Iterator<Property> iterator = persistentClass.getPropertyIterator();
            while (iterator.hasNext()) {
                propertyComment(persistentClass, iterator.next().getName());
            }
        }
    }

    /**
     * 为属性生成注释
     */
    private void propertyComment(PersistentClass persistentClass, String columnName) {
        try {
            String comment = getPropertyComment(persistentClass, columnName);
            if (persistentClass.getProperty(columnName).getValue().getColumnIterator().hasNext()) {
                String sqlColumnName = persistentClass.getProperty(columnName).getValue().getColumnIterator().next().getText();
                Iterator<org.hibernate.mapping.Column> columnIterator = persistentClass.getTable().getColumnIterator();
                while (columnIterator.hasNext()) {
                    org.hibernate.mapping.Column column = columnIterator.next();
                    if (sqlColumnName.equalsIgnoreCase(column.getName())) {
                        column.setComment(comment);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPropertyComment(PersistentClass persistentClass, String columnName) throws Exception {
        String comment = null;
        // Field field = ReflectUtil.getField(persistentClass.getMappedClass(), columnName);
        Field field = persistentClass.getMappedClass().getDeclaredField(columnName);
        if (field != null) {
            if (field.isAnnotationPresent(ApiModelProperty.class)) {
                comment = field.getAnnotation(ApiModelProperty.class).value();
            } else {
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), persistentClass.getMappedClass());
                Method readMethod = descriptor.getReadMethod();
                ApiModelProperty apiModelProperty = readMethod.getAnnotation(ApiModelProperty.class);
                if (apiModelProperty != null) {
                    comment = apiModelProperty.value();
                }
            }
        }
        return comment;
    }
}
