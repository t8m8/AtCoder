import strutils, sequtils, future

when isMainModule:
  var
    input = stdin.readLine.split.map(parseInt)
    (t, s) = (input[0], input[1])
    ans = 0

  for i in 0..t:
    for j in 0..t:
      var k = s - i - j
      if k >= 0 and k <= t:
        ans += 1

  echo ans