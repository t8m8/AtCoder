
# ==============================================================================

import future

type
  PrirorityQueue*[T] = object
    elms: seq[T]
    cmp: (T, T) -> int

proc initPriorityQueue*[T](cmp: (T, T) -> int): PrirorityQueue[T] =
  result.elms = newSeq[T]()
  result.cmp = cmp

template parent(cur: int): int =
  (cur - 1) div 2

template childl(cur: int): int =
  cur * 2 + 1

template childr(cur: int): int =
  cur * 2 + 2

proc upHeap[T](self: var PrirorityQueue[T], cur: var int) =
  var par = parent(cur)

  while par >= 0 and self.cmp(self.elms[cur], self.elms[par]) < 0:
    swap(self.elms[cur], self.elms[par])
    (cur, par) = (par, parent(par))

proc downHeap[T](self: var PrirorityQueue[T], cur: var int) =
  var (chl, chr) = (childl(cur), childr(cur))

  while chl < self.elms.len:
    var ch =
      if chr >= self.elms.len or self.cmp(self.elms[chl], self.elms[chr]) < 0: chl
      else: chr

    if self.cmp(self.elms[cur], self.elms[ch]) < 0:
      break

    swap(self.elms[cur], self.elms[ch])
    (cur, chl, chr) = (ch, childl(cur), childr(cur))

proc len*[T](self: PrirorityQueue[T]): int =
  self.elms.len

proc push*[T](self: var PrirorityQueue[T], data: T) =
  var
    n = self.elms.len
    cur = n
  self.elms.setLen(n + 1)
  self.elms[cur] = data
  self.upHeap(cur)

proc pop*[T](self: var PrirorityQueue[T]): T =
  assert self.elms.len > 0
  var
    n = self.elms.len
    cur = 0
  result = self.elms[0]
  self.elms[cur] = self.elms[n-1]
  self.elms.del(n-1)
  self.downHeap(cur)

proc delete*[T](self: var PrirorityQueue[T], data: T) =
  var
    n = self.elms.len
    idx = -1
  for i in 0..<n:
    if self.elms[i] == data:
      idx = i
      break
  if idx == -1:
    return
  elif idx == n - 1:
    self.elms.del(idx)
  else:
    self.elms[idx] = self.elms[n-1]
    self.elms.del(n-1)
    self.downHeap(idx)
    self.upHeap(idx)

# ==============================================================================

import strutils, sequtils, algorithm, future

type
  G = seq[seq[tuple[to: int, dist: int]]]

const Inf = 1 shl 29

proc shortestPaths*(g: G, start: int): (seq[int], seq[int]) =
  var
    n = g.len
    dist = newSeq[int](n)
    prev = newSeq[int](n)
    pQ = initPriorityQueue[int](
      proc(a: int, b: int): int =
        if dist[a] != dist[b]: dist[a] - dist[b]
        else: a - b
    )

  dist.fill(Inf)
  prev.fill(-1)
  pQ.push(start)
  dist[start] = 0

  while pQ.len > 0:
    var cur = pQ.pop()
    for next in g[cur]:
      var alt = dist[cur] + next.dist
      if alt < dist[next.to]:
        dist[next.to] = alt
        prev[next.to] = cur
        pQ.delete(next.to)
        pQ.push(next.to)

  result = (dist, prev)


when isMainModule:
  var
    input = stdin.readLine.split.map(parseInt)
    (n, m) = (input[0], input[1])
    g: G = newSeqWith(n, newSeq[(int, int)]())

  for i in 0..<m:
    var
      input = stdin.readLine.split.map(parseInt)
      (a, b, c) = (input[0]-1, input[1]-1, input[2])
    g[a].add((b, c))
    g[b].add((a, c))

  var used =  newSeqWith(n, newSeq[bool](n))

  for cur in 0..<n:
    var (_, prev) = shortestPaths(g, cur)
    for i in 0..<n:
      if prev[i] >= 0:
        used[i][prev[i]] = true
        used[prev[i]][i] = true
  var ans = 0
  for i in 0..<n:
    for j in g[i]:
      if not used[i][j.to]:
        ans += 1
  echo ans div 2