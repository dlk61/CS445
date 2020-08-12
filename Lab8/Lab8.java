/*
    The big O() growth rate of the number of uniques strings 
    that can be formed in an NxN Boggle grid of all unique symbols
    is 7^n^2. This is because 7 is about the average number of choices the player can 
    make by only connecting adjacent letters. This will form an upperbound for each board as the board 
    gradually gets larger. As an example, the actual number of unique strings
    a 2x2 board makes is 64, and 7^2^2 creates 2,401 strings which is an upperbound.
    The actual number of unique strings a 3x3 board makes is 10,305, and 7^3^2 
    creates 4 x 10^7 strings. A 4x4 board creates 1.2 x 10^6 unique strings, and 
    7^4^2 creaes 3.3 x 10^13 strings. As the board gets increasingly larger, using 
    the big O() growth rate of 7^n^2 will cause the number of unique strings to 
    converge.  
*/