package com.vv.trace.plugin.bytecode;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public final class TraceClassAdapter extends ClassVisitor{

    private final String TAG= "TraceClassAdapter";
    private String className;

    TraceClassAdapter(final ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
//        if(isHeritedFromBlockHandler) {
//            return mv;
//        } else {
//            return mv == null ? null :
              return new TraceMethodAdapter(className, name,access, desc, mv);
//        return new LinelogMethodAdapter(mv);
//        return new TraceVisitMethodAdapter(api,mv,access,name,desc,className);
//        }
    }



}