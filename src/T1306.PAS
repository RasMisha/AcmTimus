//fpc 3.0.0
var
i,t,n, j, n2, cnt, v:longint;
max_n: integer;
res:real;
s:Int64;
node_idx, parent_idx, left, right, idx:integer;
a:array[0..125010] of longint;
begin
    readln(n);
    max_n := (n div 2);
    {writeln('max_n = ', max_n);}

    if (n mod 2 = 0) then cnt := 2 else cnt := 1;

    for i:=0 to (n-1) do
       begin
          readln(v);

          if (i <= max_n) then
          begin
              a[i] := v;

              node_idx := i;
              parent_idx := (node_idx-1) shr 1;

              while (parent_idx >= 0) AND (a[node_idx] < a[parent_idx]) do
              begin
                  t := a[node_idx];
                  a[node_idx] := a[parent_idx];
                  a[parent_idx] := t;

                  node_idx := parent_idx;
                  parent_idx := (node_idx-1) shr 1;
              end;
          end else
          begin
                 {writeln('v = ',v);
                 for j:=0 to max_n do write(a[j], ' ');
                 writeln;}
              if (v>a[0]) then
              begin
                   a[0] := v;

                   idx := 0;
                   while TRUE do
                   begin
                       left := idx*2 + 1;
                       right := idx*2 + 2;

                       {writeln('left = ',left);
                       writeln('right = ',right);}

                       if (left > max_n) OR ((left = max_n) AND (a[idx] < a[left])) OR ((a[idx] < a[left]) AND (a[idx] < a[right])) then break;

                       if ((left = max_n) OR (a[left] <= a[right])) then
                           begin
                               t := a[left];
                               a[left] := a[idx];
                               a[idx] := t;

                               idx := left;
                           end
                       else
                           begin
                               t := a[right];
                               a[right] := a[idx];
                               a[idx] := t;

                               idx := right;
                           end;
                   end;
              end;
          end
       end;

       {for i:=0 to max_n do write(a[i], ' ');
       writeln;}

       n2 := n div 2;
       if n > max_n then n := max_n;

       while (n > n2 + 1) do
       begin
           a[0] := a[n-1];
           a[n-1] := -1;
           dec(n);

           idx := 0;
           while TRUE do
           begin
               left := idx*2 + 1;
               right := idx*2 + 2;

               if (left >= n) OR ((left = (n-1)) AND (a[idx] < a[left])) OR ((a[idx] < a[left]) AND (a[idx] < a[right])) then break;

               if (a[left] <= a[right]) then
                   begin
                       t := a[left];
                       a[left] := a[idx];
                       a[idx] := t;

                       idx := left;
                   end
               else
                   begin
                       t := a[right];
                       a[right] := a[idx];
                       a[idx] := t;

                       idx := right;
                   end;
           end;
       end;

    {for i:=0 to max_n do write(a[i], ' ');
    writeln;
    writeln('n = ', n);}

    if (cnt = 1) then writeln(a[0])
    else
        begin
            if (n >= 2) AND (a[2] < a[1]) then a[1] := a[2];
            {writeln('a[0]', a[0]);
            writeln('a[1]', a[1]);}
            res := (a[1]-a[0])/2;
            res := res + a[0];
            writeln(res:0:1);
        end;
end.