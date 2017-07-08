import strutils, sequtils, algorithm, future

{.warning[SmallLshouldNotBeUsed]: off.}

when isMainModule:
  var a = stdin.readLine.split.map(parseInt)
  echo max(a[0]*a[1], a[2]*a[3])