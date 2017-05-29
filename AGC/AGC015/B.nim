import strutils, sequtils, future

when isMainModule:
  var
    s = stdin.readLine
    n = s.len
    ans = 0

  for i in 0..<n:
    if s[i] == 'U':
      ans += n - i - 1
      ans += i * 2
    else:
      ans += (n - i - 1) * 2
      ans += i

  echo ans