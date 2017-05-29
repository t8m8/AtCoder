import strutils, sequtils, future

when isMainModule:
  var
    s = stdin.readline

  for i in 0..<s.len:
    if s[i] == ',':
      stdout.write(' ')
    else:
      stdout.write(s[i])
  echo()