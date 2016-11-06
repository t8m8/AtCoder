import strutils, sequtils

when isMainModule:
  var
    t = stdin.readLine.split.map(parsefloat)
    r = t[2] / t[0]
  echo r*t[1]
