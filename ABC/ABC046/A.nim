import strutils, sequtils, algorithm, future

{.warning[SmallLshouldNotBeUsed]: off.}

when isMainModule:
  var
    a = stdin.readLine.split.map(parseInt)
    s = {a[0], a[1], a[2]}
  echo s.card