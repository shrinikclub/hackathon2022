


let NO_PARENT = -1;
function preload()
{
  mp = loadImage("https://res.cloudinary.com/sidd293/image/upload/v1654323881/map_cjyxdh.png")
}
 
function dijkstra(adjacencyMatrix,startVertex)
{
    let nVertices = adjacencyMatrix[0].length;
   
        // shortestDistances[i] will hold the
        // shortest distance from src to i
        let shortestDistances = new Array(nVertices);
   
        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        let added = new Array(nVertices);
   
        // Initialize all distances as
        // INFINITE and added[] as false
        for (let vertexIndex = 0; vertexIndex < nVertices;
                                            vertexIndex++)
        {
            shortestDistances[vertexIndex] = Number.MAX_VALUE;
            added[vertexIndex] = false;
        }
           
        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0;
   
        // Parent array to store shortest
        // path tree
        let parents = new Array(nVertices);
   
        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;
   
        // Find shortest path for all
        // vertices
        for (let i = 1; i < nVertices; i++)
        {
   
            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            let nearestVertex = -1;
            let shortestDistance = Number.MAX_VALUE;
            for (let vertexIndex = 0;
                     vertexIndex < nVertices;
                     vertexIndex++)
            {
                if (!added[vertexIndex] &&
                    shortestDistances[vertexIndex] <
                    shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
   
            // Mark the picked vertex as
            // processed
            added[nearestVertex] = true;
   
            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (let vertexIndex = 0;
                     vertexIndex < nVertices;
                     vertexIndex++)
            {
                let edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];
                   
                if (edgeDistance > 0
                    && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                                                       edgeDistance;
                }
            }
        }
   
        printSolution(startVertex, shortestDistances, parents);
}
 
function printSolution(startVertex,distances,parents)
{
     let nVertices = distances.length;
        document.write("Vertex     Distance   Path");
           
        for (let vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
        {
            if (vertexIndex != startVertex)
            {
                document.write("<br>" + startVertex + " to  ");
                document.write(vertexIndex + "        ");
                document.write("path would be");
              
                printPath(vertexIndex, parents);
            }
        }
}
 
function printPath(currentVertex,parents)
{
     // Base case : Source node has
        // been processed
        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        document.write(currentVertex + " ");
}
 
 
let graph = 
       [[0, 4, 0, 0, 0, 0, 0, 8, 0],
        [4, 0, 8, 0, 0, 0, 0, 11, 0],
        [0, 8, 0, 7, 0, 4, 0, 0, 2],
        [0, 0, 7, 0, 9, 14, 0, 0, 0],
        [0, 0, 0, 9, 0, 10, 0, 0, 0],
        [0, 0, 4, 14, 10, 0, 2, 0, 0],
        [0, 0, 0, 0, 0, 2, 0, 1, 6],
        [8, 11, 0, 0, 0, 0, 1, 0, 7],
        [0, 0, 2, 0, 0, 0, 6, 7, 0]
        ];
dijkstra(graph,0)   
 
xa = []
ya = []

function setup() {
  createCanvas(400, 400);
  for(  i = 0;i<10;i++)
  {
    xa.push((50 + random(width - 100)).toFixed(0))
    ya.push((50 + random(height - 100)).toFixed(0))

  }

}

// d = 

function draw() {
  
  image(mp,0,0,width,height)
  background(220,220,220,100);
stroke(200,200,0)
  for(  i = 1;i<10;i++)
  {
point(xa[i],ya[i])
    push()
noStroke()
    text(`vertex: ${xa[i]},${ya[i]}`,xa[i],ya[i])
pop()
    strokeWeight(4)
    
  line(xa[i],ya[i],xa[i+1],ya[i+1])
  }
}


