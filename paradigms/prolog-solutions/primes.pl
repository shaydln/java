isDividing(N, R) :-
    (N mod R =:= 0 -> true ; false).

prime(N, R) :-
    (R =< sqrt(N) -> (isDividing(N, R) -> false ; NewR is R + 1, prime(N, NewR)) ; true).

prime(N) :-
    N >= 2,
    prime(N, 2).

composite(N) :- 
    N >= 2,
    \+ prime(N).

prime_divisors(N, Divisors) :-
    prime_divisors(N, 2, Divisors).

prime_divisors(1, _, []).

prime_divisors(N, R, [R | Divisors]) :-
    prime(R),
    N mod R =:= 0,
    NewN is N // R,
    prime_divisors(NewN, R, Divisors).

prime_divisors(N, R, Divisors) :-
    N > R,
    NewR is R + 1,
    prime_divisors(N, NewR, Divisors).

cube_divisors(N, Divisors) :-
    NewN is (N * N * N),
    prime_divisors(NewN, Divisors),
    !.

