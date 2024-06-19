package org.richfaces.cdk.templatecompiler.el.types;

import java.util.List;

import org.richfaces.cdk.templatecompiler.builder.model.JavaImport;

public class MethodTypesWrapper implements ELType {

    private List<ELType> types;
    
    public List<ELType> getTypes() {
        return types;
    }

    public MethodTypesWrapper(List<ELType> types) {
        this.types = types;
    }

    @Override
    public Iterable<JavaImport> getRequiredImports() {
        return types.get(0).getRequiredImports();
    }

    @Override
    public String getCode() {
        return types.get(0).getCode();
    }

    @Override
    public String getRawName() {
        return types.get(0).getRawName();
    }

    @Override
    public boolean isNullType() {
        return types.get(0).isNullType();
    }

    @Override
    public boolean isArray() {
        return types.get(0).isArray();
    }

    @Override
    public ELType getRawType() {
        return types.get(0).getRawType();
    }

    @Override
    public ELType getContainerType() {
        return types.get(0).getContainerType();
    }

    @Override
    public ELType[] getTypeArguments() {
        return types.get(0).getTypeArguments();
    }

    @Override
    public boolean isAssignableFrom(ELType anotherType) {
        return types.get(0).isAssignableFrom(anotherType);
    }

    @Override
    public boolean isPrimitive() {
        return types.get(0).isPrimitive();
    }

}
