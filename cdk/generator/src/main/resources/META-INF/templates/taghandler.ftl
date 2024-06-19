<#include "_copyright.ftl">

package ${tag.targetClass.package};

import java.io.Serializable;
<#if model.hasBindingAttribute=true>
import jakarta.el.MethodExpression;
import jakarta.el.ELException;
import jakarta.el.MethodExpression;
import jakarta.faces.context.FacesContext;
import jakarta.faces.el.EvaluationException;
import jakarta.el.MethodExpression;
import jakarta.faces.el.MethodNotFoundException;
import jakarta.faces.view.facelets.FaceletContext;
import jakarta.faces.view.facelets.MetaRule;
import jakarta.faces.view.facelets.MetaRuleset;
import jakarta.faces.view.facelets.Metadata;
import jakarta.faces.view.facelets.MetadataTarget;
import jakarta.faces.view.facelets.TagAttribute;
</#if>
import jakarta.faces.view.facelets.${model}Config;
import ${model.targetClass};
import ${tag.baseClass};
<#list model.tagImports as importedClass>import ${importedClass.name};
</#list>

public class ${tag.targetClass.simpleName} extends ${tag.baseClass.simpleName} {


    public ${tag.targetClass.simpleName}(${model}Config config) {
        super(config);
        <#list model.requiredAttributes as prop>getRequiredAttribute("${prop.name}");
        </#list>

    }

<#if model.hasBindingAttribute=true>
<#assign useMethodBinding=false />
    private static final MetaRule META_RULE = new MetaRule(){

        public Metadata applyRule(String name, final TagAttribute attribute, MetadataTarget meta) {
            if (meta.isTargetInstanceOf(${model.targetClass.simpleName}.class)) {
            <#list model.tagAttributes as prop><#if (prop.binding || prop.bindingAttribute)>
                if ("${prop.name}".equals(name)) {
                    return new Metadata() {
                        private final Class<?>[] SIGNATURE={<#if prop.signature?exists><@util.concat seq=prop.signature.parameters; parameter>${parameter.simpleName}.class</@util.concat></#if>};
                        public void applyMetadata(FaceletContext ctx, Object instance) {
                            ((${model.targetClass.simpleName}) instance).${prop.setterName}(getValue(ctx));
                        }
                        <#if (prop.isBinging)>
                        <#assign useMethodBinding=true />
                        @SuppressWarnings("deprecation")
                        private MethodBinding getValue(FaceletContext ctx){
                           return new MethodBindingWrapper(attribute.getMethodExpression(ctx, <#if prop.signature?exists>${prop.signature.returnType.simpleName}.class<#else>null</#if>, SIGNATURE));
                        }
                        <#else>
                        private MethodExpression getValue(FaceletContext ctx){
                           return attribute.getMethodExpression(ctx, <#if prop.signature?exists>${prop.signature.returnType.simpleName}.class<#else>null</#if>, SIGNATURE);
                        }
                        </#if>                        
                    };
                }
            </#if></#list>
            }
            return null;
        }
    };
    
    protected MetaRuleset createMetaRuleset(Class type) {
        MetaRuleset m = super.createMetaRuleset(type);
        m.addRule(META_RULE);
        return m;
    }

<#if useMethodBinding=true>
    @SuppressWarnings({ "serial", "deprecation" })
    public static final class MethodBindingWrapper extends
            MethodBinding implements Serializable {
    
        private final MethodExpression m;
    
        public MethodBindingWrapper(MethodExpression m) {
            this.m = m;
        }
    
        public Class getType(FacesContext context)
                throws MethodNotFoundException {
            try {
                return m.getMethodInfo(context.getELContext()).getReturnType();
            } catch (jakarta.el.MethodNotFoundException e) {
                throw new MethodNotFoundException(e.getMessage(), e.getCause());
            } catch (ELException e) {
                throw new EvaluationException(e.getMessage(), e.getCause());
            }
        }
    
        public Object invoke(FacesContext context, Object[] params)
                throws EvaluationException, MethodNotFoundException {
            try {
                return m.invoke(context.getELContext(), params);
            } catch (jakarta.el.MethodNotFoundException e) {
                throw new MethodNotFoundException(e.getMessage(), e.getCause());
            } catch (ELException e) {
                throw new EvaluationException(e.getMessage(), e.getCause());
            }
        }
    
        public String getExpressionString() {
            return m.getExpressionString();
        }
    }
</#if></#if>
}
