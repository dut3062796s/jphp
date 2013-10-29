package ru.regenix.jphp.compiler.jvm.runtime.memory;

public class TrueMemory extends Memory {

    public static TrueMemory INSTANCE = new TrueMemory();

    protected TrueMemory() {
        super(Type.BOOL);
    }

    @Override
    public long toLong() {
        return 1;
    }

    @Override
    public double toDouble() {
        return 1;
    }

    @Override
    public boolean toBoolean() {
        return true;
    }

    @Override
    public String toString() {
        return "1";
    }

    @Override
    public Memory toNumeric(){
        return Memory.CONST_INT_1;
    }

    @Override
    public Memory plus(Memory memory) {
        switch (memory.type){
            case INT: return new LongMemory(1 + ((LongMemory)memory).value);
            case DOUBLE: return new DoubleMemory(1 + ((DoubleMemory)memory).value);
            default: return memory.toNumeric().plus(CONST_INT_1);
        }
    }

    @Override
    public Memory minus(Memory memory) {
        switch (memory.type){
            case INT: return new LongMemory(1 - ((LongMemory)memory).value);
            case DOUBLE: return new DoubleMemory(1 - ((DoubleMemory)memory).value);
            default: return memory.toNumeric().minus(CONST_INT_1);
        }
    }

    @Override
    public Memory mul(Memory memory) {
        switch (memory.type){
            case INT:
            case DOUBLE: return memory;
            default: return memory.toNumeric();
        }
    }

    @Override
    public Memory div(Memory memory) {
        switch (memory.type){
            case DOUBLE: return new DoubleMemory(1 / ((DoubleMemory)memory).value);
            default: return memory.toNumeric().minus(CONST_INT_1);
        }
    }

    @Override
    public Memory mod(Memory memory) {
        switch (memory.type){
            case INT:
                if (((LongMemory)memory).value == 1)
                    return CONST_INT_1;
                else
                    return CONST_INT_0;
            case DOUBLE:
                return CONST_DOUBLE_0;
            default:
                return CONST_INT_0;
        }
    }

    @Override
    public boolean equal(Memory memory) {
        return toBoolean();
    }

    @Override
    public boolean notEqual(Memory memory) {
        return !toBoolean();
    }

    @Override
    public Memory concat(Memory memory) {
        switch (memory.type){
            case STRING: return new StringMemory(toString() + ((StringMemory)memory).value);
            default:
                return new StringMemory(toString() + memory.toString());
        }
    }

    @Override
    public boolean smaller(Memory memory) {
        return !memory.toBoolean();
    }

    @Override
    public boolean smallerEq(Memory memory) {
        return memory.toBoolean();
    }

    @Override
    public boolean greater(Memory memory) {
        return false;
    }

    @Override
    public boolean greaterEq(Memory memory) {
        return memory.toBoolean();
    }

    @Override
    public Memory minus(long value) {
        return new LongMemory(1 - value);
    }
}
