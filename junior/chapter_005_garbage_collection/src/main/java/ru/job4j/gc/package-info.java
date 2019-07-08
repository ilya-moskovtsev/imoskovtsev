/**
 * Garbage collection.
 * <p>
 * VM options: -Xmx80m
 * 1000 objects with one field took 526336 bytes
 * 1000 objects without fields took 0 bytes
 * <p>
 * VM options: -Xmx8m
 * Java Virtual Machine reclaims space
 * 5:18:34.523 [main] INFO  ru.job4j.gc.GarbageCollectorDemo - created User Name 177
 * 15:18:34.523 [Finalizer] INFO  ru.job4j.gc.GarbageCollectorDemo - finalized User Name 110
 *
 * @author imoskovtsev
 * @version $Id$
 * @since 0.1
 */
package ru.job4j.gc;