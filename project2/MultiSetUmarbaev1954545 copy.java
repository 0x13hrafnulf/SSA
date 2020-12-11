
/* JML Exercise: 
   
   This class implements a MultiSet of integers, using an array.

   Add JML specs to this class, and if necessary modify the code, to stop openJML from complaining.


   The only JML keywords needed are
      requires
      invariant 
      ensures 
  
   If you want, you can also use
      non_null
   as abbreviation.


   While developing your specs, it may be useful to use the keyword
      assert
   to add additional assertions in source code, to find out what 
   JML can - or cannot - prove at a given program point.


*/

class MultiSet {
 
  /*@ non_null @*/ int[] elements;
  int n;
  //@ invariant n >= 0;
  //@ invariant n <= elements.length;
  //@ invariant n <= Integer.MAX_VALUE;
  //@ invariant elements.length >= 0;
  //@ invariant elements.length <= Integer.MAX_VALUE;


  MultiSet(/*@ non_null @*/ int[] input) 
  {
    if(input != null && input.length >= 0)
    {
      n = input.length;
      elements = new int[n];
      arraycopy(input, 0, elements, 0, n);
    }
    
  }
  
  //@pure
  MultiSet() 
  {
    elements = new int[0];
    n = 0;
  }

  //@ requires n >= 0;
  //@ requires n <= elements.length;
  //@ requires n <= Integer.MAX_VALUE;
  //@ requires elements.length >= 0;
  //@ requires elements.length <= Integer.MAX_VALUE;
  void removeOnce(int elt) {
    //@ loop_invariant i >= 0;
    for (int i = 0; i < n && i < elements.length; i++) { 
      if (elements[i] == elt && n <= elements.length) 
      { 
         n = n - 1;
         elements[i] = elements[n];
         return;
      }
    }
  }

  /* the next method should remove ALL occurrences of the parameter int elt.
   */

  //@ requires n >= 0;
  //@ requires n <= elements.length;
  //@ requires n <= Integer.MAX_VALUE;
  //@ requires elements.length >= 0;
  //@ requires elements.length <= Integer.MAX_VALUE;
  void removeAll(int elt) {
    //@ loop_invariant i >= 0;
    for (int i = 0; i < n && i < elements.length; i++) {
      if (elements[i] == elt && n <= elements.length) 
      {
         n = n - 1;    
         elements[i] = elements[n];
      }
    }
  }

  //@ requires n >= 0;
  //@ requires n <= elements.length;
  //@ requires n <= Integer.MAX_VALUE;
  //@ requires elements.length >= 0;
  //@ requires elements.length <= Integer.MAX_VALUE;
  int getCount(int elt) {
    int count = 0;
    //@ loop_invariant i >= 0;
    for (int i = 0; i < n && i < elements.length; i++)
    {
      if (elements[i] == elt)
      {
        if(count < Integer.MAX_VALUE) count = count + 1;
      }
    }
    return count;
  }

  /* Warning: you may have a hard time checking the method "add" below.
   */

 
  //@ requires n >= 0;
  //@ requires n <= elements.length;
  //@ requires n <= Integer.MAX_VALUE;
  //@ requires elements.length >= 0;
  //@ requires elements.length <= Integer.MAX_VALUE;
  //@ ensures n <= elements.length;
  void add(int elt) {
    if (n == elements.length) {
        int size = Math.multiplyExact(n, 2);
        if(size > 0)
        {
          int[] new_elements = new int[size]; 
          arraycopy(elements, 0, new_elements, 0, n);
          elements = new_elements;
        }
    }
    if(n >= 0 && n < elements.length)
    {
      elements[n]=elt;
      n = n + 1;
    }
  }

  //@ requires n >= 0;
  //@ requires n <= elements.length;
  //@ requires n <= Integer.MAX_VALUE;
  //@ requires elements.length >= 0;
  //@ requires elements.length <= Integer.MAX_VALUE;
  void add(/*@ non_null @*/ MultiSet b) 
  {
      int size = Math.addExact(n, b.n);
      if(size > 0)
      {
        n = size;
        int[] new_elements = new int[size];
        arraycopy(elements, 0, new_elements, 0, n);
        arraycopy(b.elements, 0, new_elements, Math.addExact(n, 1), b.n);   
        elements = new_elements; 
      }
  }

  void add(/*@ non_null @*/ int[] a) 
  {
    if(a != null)
    {
      this.add(new MultiSet(a));
    }
  }


  MultiSet(/*@ non_null @*/ MultiSet b) 
  {
    if(b != null)
    {
      this.add(b);
    }
        
  }

  //@ requires length >= 0;
  //@ requires srcOff >= 0;
  //@ requires destOff >= 0;
  //@ requires src.length >= 0;
  //@ requires dest.length >= 0;
  //@ requires srcOff + length <= src.length;
  //@ requires destOff + length <= dest.length;
  //@ requires length <= src.length && length <= dest.length;
  private static void arraycopy(/*@ non_null @*/ int[] src,
                                int   srcOff,
                                /*@ non_null @*/ int[] dest,
                                int   destOff,
                                int   length) 
                                {
    //@ loop_invariant i >= 0;
    for( int i = 0 ; i < length; i++) 
    {
       dest[destOff+i] = src[srcOff+i];
    }
  }
  
}
