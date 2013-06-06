package com.trebogeer.jboost.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * @author dimav
 *         Date: 5/22/13
 *         Time: 10:54 AM
 */
public class DirectByteBufferOutputStream extends OutputStream {


    public static final int buffer_size = Integer.getInteger("jboost.direct.buffer.size.bytes", /*2560000*/3 * 4096);
    // TODO soft ref it
    private static final ThreadLocal<ByteBuffer> buffers = new ThreadLocal<ByteBuffer>() {
        @Override
        protected ByteBuffer initialValue() {
            ByteBuffer bb = ByteBuffer.allocateDirect(buffer_size);
            bb.mark();
            return bb;
        }

        @Override
        public ByteBuffer get() {
            final ByteBuffer bb = super.get();
            bb.reset();
          //  bb.rewind();
            return bb;
        }
    };


    private DirectByteBufferOutputStream() {
        byteBuffer = buffers.get();
    }

    private ByteBuffer byteBuffer;

    @Override
    public void write(int b) throws IOException {
        byteBuffer.put((byte)b);
    }

    public byte[] toByteArray() {
        byte[] b = new byte[byteBuffer.remaining()];
        byteBuffer.get(b);
        return b;
    }

    public static DirectByteBufferOutputStream getDirectBufferStream() {
        return new DirectByteBufferOutputStream();
    }
}
