import strutils, sequtils, future

when isMainModule:
  var
    input = stdin.readLine.split.map(parseInt)
    (n, a, b) = (input[0], input[1], input[2])

  if a > b or (n == 1 and a != b):
    echo 0
  elif a == b:
    echo 1
  else:
    echo(((n-1)*b + a) - ((n-1)*a + b) + 1)