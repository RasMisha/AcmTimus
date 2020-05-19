procedure add(v: integer; var heap: array of integer; var heapSize: integer);
var
nextIdx, idx, t: integer;
begin
        heap[heapSize] := v;
        idx := heapSize;
        nextIdx := (idx+1) div 2;
        while (idx > 0) AND (heap[nextIdx] > v) do
        begin
            t := heap[idx];
            heap[idx] := heap[nextIdx];
            heap[nextIdx] := t;

            idx := nextIdx;
            nextIdx := (idx+1) div 2;
        end;
        inc(heapSize);
end;

function poll(var heap:array of integer; var heapSize: integer): integer;
var
v, curIdx, left, right, t, j: integer;
begin
    poll := heap[0];

    v := heap[heapSize-1];
    curIdx := 0;
    heap[curIdx] := v;
    dec(heapSize);

    while curIdx < heapSize do
    begin
        left := curIdx*2 + 1;
        right := curIdx*2 + 2;

        j := left;
        if (right < heapSize) AND (heap[right] < heap[left]) then j := right;
        if (heap[curIdx] <= heap[j]) then break;

        t := heap[curIdx];
        heap[curIdx] := heap[j];
        heap[j] := t;

        curIdx := j;
    end;
end;

function solve(): string;
var
s: string;
n, heapSize, idx,i,k: integer;
heap: array[0..100] of integer;
words: array[1..6] of string;
w: string;
can: boolean;
begin

    words[1] := 'in';
    words[2] := 'one';
    words[3] := 'out';
    words[4] := 'input';
    words[5] := 'puton';
    words[6] := 'output';

    readln(s);
    solve := 'NO';
    n := length(s);

    heapSize := 0;
    add(0, heap, heapSize);

    while (heapSize > 0) do
    begin
        idx := poll(heap, heapSize);
        if (idx = n) then
        begin
            solve := 'YES';
            break;
        end;
        inc(idx);

        for k := 1 to 6 do begin
            w := words[k];
            if (idx + length(w) - 1 <= n) then
            begin
                can := true;
                for i := 1 to length(w) do
                begin
                    if (w[i] <> s[idx+i-1]) then
                    begin
                        can := false;
                        break;
                    end;
                end;
                if (can = true) then
                    add(idx+length(w)-1, heap, heapSize);
            end;
        end;
    end;
end;



var
i,n: integer;
begin
readln(n);
for i := 1 to n do
begin
    writeln(solve());
end;
end.