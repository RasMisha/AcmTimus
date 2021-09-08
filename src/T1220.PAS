var
i,n:integer;
com:string[4];

sss:array[0..120000] of integer;
bl:array[1..1000] of word;
nbl:array[1..1000] of word;
prevbl: array[1..12000] of word;
blocksize,blused:integer;
idx:array[1..1000] of integer;
numberOfBl, cntOfBl: word;

a:word;
b,len:integer;
block, rem, ind: integer;
begin
blocksize := 50;
readln(n);

blused := 0;
for i:=1 to 1000 do
 begin
    idx[i] := 0;
    nbl[i] := 0;
    bl[i] := 0;
 end;

for i:=1 to n do
 begin
  read(com);
  if (com='PUSH')
   then
    begin
     readln(a,b);
     ind := idx[a] mod 50000;
     if ((ind+1)>(nbl[a]*blocksize)) then
      begin

       prevbl[blused]:=bl[a];
       bl[a] := blused;

       inc(blused);
       inc(nbl[a]);
      end;

     block := ind div blocksize;
     rem := ind mod blocksize;


     cntOfBl := nbl[a];
     numberOfBl := bl[a];

     while cntOfBl > block+1 do
       begin
            numberOfBl := prevbl[numberofbl];
            dec(cntOfBl);
       end;

     sss[numberOfBl*blocksize + rem] := b;
     inc(idx[a]);
    end
   else
    begin
     readln(a);
     dec(idx[a]);

     ind := idx[a] mod 50000;
     block := ind div blocksize;
     rem := ind mod blocksize;

     cntOfBl := nbl[a];
     numberOfBl := bl[a];

     while cntOfBl > block+1 do
       begin
            numberOfBl := prevbl[numberofbl];
            dec(cntOfBl);
       end;

    {write('numberOfBl*blocksize+rem = ');
    writeln(numberOfBl*blocksize+rem);}
     writeln(sss[numberOfBl*blocksize+rem]);
    end;
 end;
end.