import strutils, sequtils, tables

proc gcd(a, b: int):int =
  if b == 0: result = a
  else: result = gcd(b, a mod b)

when isMainModule:
  var
    t = stdin.readLine.split.map(parseInt)
    (n, k) = (t[0], t[1])
    cnt = newCountTable[int]()
    ans = 0

  for i in stdin.readLine.split.map(parseInt):
    cnt.inc(gcd(i, k))

  for i in cnt.pairs:
    for j in cnt.pairs:
      if i[0]*j[0] mod k == 0:
        if i[0] == j[0]:
          ans += i[1]*(j[1]-1) div 2
        elif i[0] < j[0]:
          ans += i[1]*j[1]

  ans.echo