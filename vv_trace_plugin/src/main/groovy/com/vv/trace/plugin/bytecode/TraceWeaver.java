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
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        return new TraceClassAdapter(classWriter);
    }

}
