package com.carrotsearch.hppc;

import static com.carrotsearch.hppc.TestUtils.assertEquals2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Various API expectations from generated classes.
 */
public class APIExpectationsTest
{
    public volatile int [] t1;

    @Test
    public void testPrimitiveToArray()
    {
        t1 = IntArrayList.from(1, 2, 3).toArray();
        t1 = IntStack.from(1, 2, 3).toArray();
        t1 = IntArrayDeque.from(1, 2, 3).toArray();
        t1 = IntOpenHashSet.from(1, 2, 3).toArray();

        t1 = IntObjectOpenHashMap.from(
            new int [] {1, 2}, new Long [] {1L, 2L}).keys().toArray();
    }

    @Test
    @SuppressWarnings("unused")
    public void testNewInstance()
    {
        IntArrayList v1 = IntArrayList.newInstance();
        ObjectArrayList<Integer> v2 = ObjectArrayList.newInstance();
        ObjectArrayList<Long> v3 = ObjectArrayList.newInstance();
        
        IntStack v4 = IntStack.newInstance();
        ObjectStack<Integer> v5 = ObjectStack.newInstance();
        ObjectStack<Long> v6 = ObjectStack.newInstance();
        
        IntOpenHashSet v7 = IntOpenHashSet.newInstance();
        ObjectOpenHashSet<Integer> v8 = ObjectOpenHashSet.newInstance();
        ObjectOpenHashSet<Long> v9 = ObjectOpenHashSet.newInstance();
        
        IntArrayDeque v10 = IntArrayDeque.newInstance();
        ObjectArrayDeque<Integer> v11 = ObjectArrayDeque.newInstance();
        ObjectArrayDeque<Long> v12 = ObjectArrayDeque.newInstance();

        IntIntOpenHashMap v13 = IntIntOpenHashMap.newInstance();
        ObjectIntOpenHashMap<Integer> v14 = ObjectIntOpenHashMap.newInstance();
        IntObjectOpenHashMap<Integer> v15 = IntObjectOpenHashMap.newInstance();
    }

    @Test
    public void testObjectToArray()
    {
        isObjectArray(ObjectArrayList.from(1, 2, 3).toArray());
        isObjectArray(ObjectStack.from(1, 2, 3).toArray());
        isObjectArray(ObjectArrayDeque.from(1, 2, 3).toArray());
        isObjectArray(ObjectOpenHashSet.from(1, 2, 3).toArray());

        isObjectArray(ObjectObjectOpenHashMap.from(
            new Integer [] {1, 2}, new Long [] {1L, 2L}).keys().toArray());
    }

    @Test
    public void testWithClassToArray()
    {
        isIntegerArray(ObjectArrayList.from(1, 2, 3).toArray(Integer.class));
        isIntegerArray(ObjectStack.from(1, 2, 3).toArray(Integer.class));
        isIntegerArray(ObjectArrayDeque.from(1, 2, 3).toArray(Integer.class));
        isIntegerArray(ObjectOpenHashSet.from(1, 2, 3).toArray(Integer.class));

        isIntegerArray(ObjectObjectOpenHashMap.from(
            new Integer [] {1, 2}, new Long [] {1L, 2L}).keys().toArray(Integer.class));
    }
    
    @Test
    public void testWildcards()
    {
        ObjectArrayList<? extends Number> t = ObjectArrayList.from(1, 2, 3);
        isTypeArray(Number.class, t.toArray(Number.class));

        t = ObjectArrayList.from(1L, 2L, 3L);
        isTypeArray(Number.class, t.toArray(Number.class));
    }

    @Test
    public void testPutOrAddOnEqualKeys()
    {
    	ObjectIntOpenHashMap<Integer> map = ObjectIntOpenHashMap.newInstance();

    	Integer k1  = 1;
    	Integer k1b = new Integer(k1.intValue()); 

    	assertTrue(k1 != k1b);
        assertEquals2(1, map.putOrAdd(k1, 1, 2));
        assertTrue(map.containsKey(k1b));
        assertEquals2(3, map.putOrAdd(k1b, 1, 2));
    }    

    /**
     * Check if the array is indeed of Object component type.
     */
    private void isObjectArray(Object [] array)
    {
        isTypeArray(Object.class, array);
    }
    
    /**
     * 
     */
    private void isTypeArray(Class<?> clazz, Object [] array)
    {
        assertEquals(clazz, array.getClass().getComponentType());
    }

    /**
     * Check if the array is indeed of Integer component type.
     */
    private void isIntegerArray(Integer [] array)
    {
        isTypeArray(Integer.class, array);
    }
}
