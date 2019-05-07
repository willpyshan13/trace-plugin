package com.vv.trace.plugin.bytecode;

import com.vv.trace.plugin.Log;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.LocalVariablesSorter;

public final class TraceMethodAdapter extends LocalVariablesSorter implements Opcodes {

    private String TAG = "TraceMethodAdapter";
    private int startVarIndex;

    private String methodName;
    private String className;
    private String desc;
    private boolean isHasTracked;
    private int timeLocalIndex = 0;

    public TraceMethodAdapter(String name,String className, int access, String desc, MethodVisitor mv) {
        super(Opcodes.ASM5, access, desc, mv);
        this.methodName = name;
        this.className = className;
        this.desc = desc;
    }

    @Override
    public void visitCode() {
        super.visitCode();
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
//        startVarIndex = newLocal(Type.LONG_TYPE);
//        mv.visitVarInsn(Opcodes.LSTORE, startVarIndex);
    }

    @Override
    public void visitInsn(int opcode) {
//        Log.d(TAG,"className="+className+"  methodName="+methodName+"  desc="+desc);
        if (methodName.equals("onClick")&&desc.equals("(Landroid/view/View;)V")){
            Log.d(TAG,"className="+className+"  methodName="+methodName+"  desc="+desc);
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(35, l0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "com/vv/trace/AutoTrackHelper", "trackViewOnClick", "(Landroid/view/View;)V", false);
            isHasTracked = true;

//            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
//            mv.visitVarInsn(LLOAD, timeLocalIndex);
//            mv.visitInsn(LSUB);
//            mv.visitVarInsn(LSTORE, timeLocalIndex);
//
//            int stringBuilderIndex = newLocal(Type.getType("java/lang/StringBuilder"));
//            mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
//            mv.visitInsn(Opcodes.DUP);
//            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
//            mv.visitVarInsn(Opcodes.ASTORE, stringBuilderIndex);//
//            mv.visitVarInsn(Opcodes.ALOAD, stringBuilderIndex);
//            mv.visitLdcInsn(className + "." + methodName + " time:");
//            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//            mv.visitInsn(Opcodes.POP);
//            mv.visitVarInsn(Opcodes.ALOAD, stringBuilderIndex);
//            mv.visitVarInsn(Opcodes.LLOAD, timeLocalIndex);
//            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
//            mv.visitInsn(Opcodes.POP);
//            mv.visitLdcInsn("TracePlugin");
//            mv.visitVarInsn(Opcodes.ALOAD, stringBuilderIndex);
//            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
//            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
//            mv.visitInsn(Opcodes.POP);//

        }

//        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
//            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
//            mv.visitVarInsn(LLOAD, startVarIndex);
//            mv.visitInsn(LSUB);
//            int index = newLocal(Type.LONG_TYPE);
//            mv.visitVarInsn(LSTORE, index);
//            mv.visitLdcInsn(methodName);
//            mv.visitVarInsn(LLOAD, index);
//            mv.visitMethodInsn(INVOKESTATIC, "com/hunter/library/timing/BlockManager", "timingMethod", "(Ljava/lang/String;J)V", false);
//        }

        super.visitInsn(opcode);
    }

}
