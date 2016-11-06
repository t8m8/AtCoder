import strutils, sequtils, math

proc f(i, r, n: float): float = sqrt(r*r - (r-2*i*r/n)*(r-2*i*r/n))*2

when isMainModule:
  var
    t = stdin.readLine.split.map(parsefloat)
    (r, n, m) = (t[0], t[1], t[2])
    ans: float = 0

  for i in 1..(n+m-1).int:
    var x: float = i.float
    if x > m and abs(x - n / 2) > abs(x - m - n / 2):
      ans += f((x-m), r, n)
    else:
      ans += f(x, r, n)
  ans.echo