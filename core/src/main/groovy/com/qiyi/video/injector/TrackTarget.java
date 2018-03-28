package com.qiyi.video.injector;

import java.util.List;

/**
 * Created by linjianjun on 2017/7/24.
 */
public class TrackTarget {
    public final String interfaceName;
    public final String className;
    public final String methodName;
    public final String methodDesc;
    public final int type;
    public final Inst inst;

    public TrackTarget(String interfaceName, String className, String methodName, String methodDesc, Integer type, Inst inst) {
        this.interfaceName = interfaceName;
        this.className = className;
        this.methodName = methodName;
        this.methodDesc = methodDesc;
        if (type == null)
            this.type = 0;
        else
            this.type = type.intValue();
        this.inst = inst;
    }

    public static class Inst {
        public final String owner;
        public final String methodName;
        public final String methodDesc;
        public final List<Integer> argIndexes;

        public Inst(String owner, String methodName, String methodDesc, List<Integer> argIndexes) {
            this.owner = owner;
            this.methodName = methodName;
            this.methodDesc = methodDesc;
            this.argIndexes = argIndexes;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("owner=").append(owner).append(" ")
                    .append("methodName=").append(methodName).append(" ")
                    .append("methodDesc=").append(methodDesc).append(" ")
                    .append("argIndexes=").append(argIndexes);
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("interfaceName=").append(interfaceName).append(" ");
        sb.append("className=").append(className).append(" ")
                .append("methodName=").append(methodName).append(" ")
                .append("methodDesc=").append(methodDesc).append(" ")
                .append("type=").append(type);
        sb.append("inst={").append(inst).append("}");
        return sb.toString();
    }
}
