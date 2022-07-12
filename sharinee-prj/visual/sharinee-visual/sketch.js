


let NO_PARENT = -1;
 

function preload(){
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
        // document.write("Vertex     Distance   Path");
           
        for (let vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
        {
            if (vertexIndex != startVertex)
            {
                // document.write("<br>" + startVertex + " -> ");
                // document.write(vertexIndex + "        ");
                
                console.log("distances :")
                console.log(distances[vertexIndex])

                // document.getElementById('dists').innerText = (distances[vertexIndex] + "      ");
                printPath(vertexIndex, parents);
            }

        }
console.log("next")
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
        // document.write(currentVertex + " ");
}
 
 
let graph = 
[[0, 4, 0, 0, 0, 0, 0, 0, 0],
[4, 0, 8, 0, 0, 0, 0, 0, 0],
[0, 8, 0, 7, 0, 4, 0, 0, 2],
[0, 0, 7, 0, 0, 0, 0, 0, 0],
[0, 0, 0, 9, 0, 10, 0, 0, 0],
[0, 0, 4, 0, 10, 0, 0, 0, 0],
[0, 0, 0, 0, 0, 0, 0, 0, 6],
[0, 0, 0, 0, 0, 0, 0, 0, 7],
[0, 0, 2, 0, 0, 0, 6, 7, 0]
];
dijkstra(graph,0)   
 
xa = []
ya = []



let d = 
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

        function setup() {
          createCanvas(400, 400);
          for(  i = 0;i<8;i++)
          {
            xa.push((50 + random(width - 100)).toFixed(0))
            ya.push((50 + random(height - 100)).toFixed(0))
        
          }
        
        }

   
xt  = [2,4,6,8,12,12,12,16]
yt = [4,8,2,4,2,10,4,14]
function draw() {
image(mp,0,0,height,width)
  background(220,220,200,10);
stroke(200,0,10)
strokeWeight(4)

strokeWeight(8)

line(width -100 - width/xt[0],(height+100)/yt[0],width -100 - width/xt[3],(height+100)/yt[3])
stroke(200,100,10)

strokeWeight(4)

line(width -100 - width/xt[2],(height+100)/yt[2],width -100 - width/xt[3],(height+100)/yt[3])
stroke(200,166,10)

strokeWeight(8)

line(width -100 - width/xt[3],(height+100)/yt[3],width -100 - width/xt[5],(height+100)/yt[5])

stroke(20,10,103)
strokeWeight(4)

line(width -100 - width/xt[6],(height+100)/yt[6],width -100 - width/xt[5],(height+100)/yt[5])

stroke(20,166,10)
strokeWeight(8)

line(width -100 - width/xt[5],(height+100)/yt[5],width -100 - width/xt[7],(height+100)/yt[7])



for(i = 0 ;i<8;i++)
{strokeWeight(10)
  point(width - 100 - width/xt[i],(height+100)/yt[i])
textSize(15)
noStroke()
text(  String.fromCharCode(65 + i),width  -100- width/xt[i],(height+100)/yt[i])
}
//   for(  i = 1;i<8;i++)
//   {
// point(xa[i],ya[i])

// text(`vertex: ${xa[i]}+${ya[i]}`,xa[i],ya[i])
// strokeWeight(2)
// for( j = 0;j<8;j++)
// {
//   console.log(d[i][j])
//   if(d[i][j] > 2)
//   {
//     d[i][j] = dist(xa[i],ya[i],xa[j],ya[j])
//   line(xa[i],ya[i],xa[j],ya[j])
  
//   }
// }
  
//   }
  
  // dijkstra(d,0)  
  
  
}


