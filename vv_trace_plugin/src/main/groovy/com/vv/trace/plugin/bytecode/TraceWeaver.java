package com.vv.trace.plugin.bytecode;

import com.quinn.hunter.transform.asm.BaseWeaver;
import com.vv.trace.plugin.TraceExtension;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * Created by Quinn on 09/07/2017.
 */
public final class TraceWeaver extends BaseWeaver {

    private static final String PLUGIN_LIBRARY = "com.vv.trace.library.trace";

    private TraceExtension timingHunterExtension;

    @Override
    public void setExtension(Object extension) {
        if(extension == null) return;
        this.timingHunterExtension = (TraceExtension) extension;
    }

    @Override
    public boolean isWeavableClass(String fullQualifiedClassName) {
        boolean superResult = super.isWeavableClass(fullQualifiedClassName);
        boolean isByteCodePlugin = fullQualifiedClassName.startsWith(PLUGIN_LIBRARY);
        if(timingHunterExtension != null) {
            //whitelist is prior to to blacklist
            if(!timingHunterExtension.whitelist.isEmpty()) {
                boolean inWhiteList = false;
                for(String item : timingHunterExtension.whitelist) {
                    if(fullQualifiedClassName.startsWith(item)) {
                        inWhiteList = true;
                    }
                }
                return superResult && !isByteCodePlugin && inWhiteList;
            }
            if(!timingHunterExtension.blacklist.isEmpty()) {
                boolean inBlackList = false;
                for(String item : timingHunterExtension.blacklist) {
                    if(fullQualifiedClassName.startsWith(item)) {
                        inBlackList = true;
                    }
                }
                return superResult && !isByteCodePlugin && !inBlackList;
            }
        }
        return superResult && !isByteCodePlugin;
    }

    @Override
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        return new TraceClassAdapter(classWriter);
    }

}
