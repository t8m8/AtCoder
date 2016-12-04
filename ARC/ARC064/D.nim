import strutils, sequtils

when isMainModule:
  var s = stdin.readLine
  if s[0] == s[s.len-1]:
    if s.len mod 2 == 0: echo "First"
    else: echo "Second"
  else:
    if s.len mod 2 == 0: echo "Second"
    else: echo "First"
