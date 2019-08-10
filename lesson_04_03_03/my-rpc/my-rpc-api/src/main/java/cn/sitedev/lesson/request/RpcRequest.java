package cn.sitedev.lesson.request;

import java.io.Serializable;
import java.util.Arrays;

// 需要实现序列化接口
public class RpcRequest implements Serializable {
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数值
     */
    private Object[] args;
    /**
     * 版本号
     */
    private String version;

    public RpcRequest() {
    }

    public RpcRequest(String className, String methodName, Object[] args, String version) {
        this.className = className;
        this.methodName = methodName;
        this.args = args;
        this.version = version;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", args=" + Arrays.toString(args) +
                ", version='" + version + '\'' +
                '}';
    }
}
