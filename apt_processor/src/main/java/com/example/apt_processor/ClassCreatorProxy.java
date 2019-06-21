package com.example.apt_processor;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import java.util.HashMap;
import java.util.Map;

public class ClassCreatorProxy {

    private String mBindingClassName;
    private String mPackageName;
    private TypeElement mTypeElement;
    private Map<Integer, VariableElement> mVariableElementsMap=new HashMap<>();

    public ClassCreatorProxy(Elements mElementUtils, TypeElement classElement) {
        this.mTypeElement=classElement;
        PackageElement packageElement = mElementUtils.getPackageOf(mTypeElement);
        String packageName = packageElement.getQualifiedName().toString();
        String className = mTypeElement.getSimpleName().toString();
        this.mPackageName=packageName;
        this.mBindingClassName=className+"_ViewBinding";
    }

    public void putElement(int id, VariableElement variableElement) {
        mVariableElementsMap.put(id, variableElement);
    }

    public String getProxyClassFullName() {
        return mPackageName+"."+mBindingClassName;
    }

    public Element getTypeElement() {
        return mTypeElement;
    }

    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(mPackageName).append(";\n\n");
        builder.append("import com.example.apt_lib.*;\n");
        builder.append("\n");
        builder.append("public class ").append(mBindingClassName).append("{\n");

        generateMethods(builder);
        builder.append("\n");
        builder.append("}\n");
        return builder.toString();
    }

    private void generateMethods(StringBuilder builder) {
        builder.append("public void bind("+mTypeElement.getQualifiedName()+" host ) {\n");
        for(int id: mVariableElementsMap.keySet()){
            VariableElement element = mVariableElementsMap.get(id);
            String name = element.getSimpleName().toString();
            String type = element.asType().toString();
            builder.append("host."+name).append(" = ");
            builder.append("("+type+")(((android.app.Activity)host).findViewById( "+id+"));\n");
        }
        builder.append(" }\n");
    }
}
