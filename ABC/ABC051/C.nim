import strutils, sequtils, future

when isMainModule:
  var
    input = stdin.readLine.split.map(parseInt)
    (sx, sy, tx, ty) = (input[0], input[1], input[2], input[3])
    (dx, dy) = (tx-sx, ty-sy)
    ans = ""
  ans &= repeat('R', dx) & repeat('U', dy) & repeat('L', dx) & repeat('D', dy)
  ans &= "D" & repeat('R', dx+1) & repeat('U', dy+1) & "LU" & repeat('L', dx+1) & repeat('D', dy+1) & "R"
  echo(ans)