#My Name: Ryan Potocki

import numpy as np
import pandas as pd

def scatterplot():
    """creates a scatterplot in three colors"""
    #get the data for the plots
    reddata = np.array([[1,1],[1,3],[4,2]])
    bluedata = np.array([[0,1],[0,5],[1,2],[2,3],[3,4]])
    yellowdata = np.array([[1,4],[2,2],[3,5],[6,2]])
    #convert the data to a pd DataFrame
    df = pd.DataFrame(reddata, columns=["x","y"])
    df1 = pd.DataFrame(bluedata, columns=["x","y"])
    df2 = pd.DataFrame(yellowdata, columns=["x","y"])
    #create the plot
    ax = df.plot.scatter(x="x",y="y",label="Red Group",color="Red",title="Scatter Plot in Three Colors",xlim=(-1,7),ylim=(0,6))
    ax1 = df1.plot.scatter(x="x",y="y",label="Blue Group",color="Blue",ax=ax)
    ax2 = df2.plot.scatter(x="x",y="y",label="Yellow Group",color="Yellow",ax=ax)
    #get the figure from the axes and save it
    fig = ax.get_figure()
    fig.savefig("my_scatter_plot.png")

def line_graph():
    """creates a line graph of cosine approximated at intervals of 1/(10pi)"""
    #create the data in an array
    xval = np.arange(0,6,(np.pi*(1./10)))
    yval = np.cos(xval)
    data = np.array([xval,yval])
    data = data.transpose()
    y = np.arange(-1,1.5,0.5)
    #convert the data to a pd DataFrame
    df = pd.DataFrame(data,columns=["x","y"])
    #tell the DataFrame to plot the data
    ax = df.plot(x="x",y="y",label="0",ylim=(-1,1),yticks=y,title="Cosine Approximated at Intervals of 1/(10pi)")
    ax.set(xlabel="",ylabel="")
	#get the figure from the axes and save it
    fig = ax.get_figure()
    fig.savefig("my_line_graph.png")

if __name__ == "__main__":
    line_graph()
    scatterplot()
        





