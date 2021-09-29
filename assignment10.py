#My Name: Ryan Potocki

def paragraph(line):
    """ if the line is not blank, it will print paragraph tags on both sides"""
    if len(line) > 0 and "  " not in line:
        line = "<p>"+line+"</p>"
        return line
    else:
        return line

def header(line):
    """ is the line has # or ## in it it will add header tags"""
    if "##" in line:
		line = line[3:]
		line = "<h2>"+line+"</h2>"
		return line
    elif "#" in line:
		line = line[2:]
		line = "<h1>"+line+"</h1>"
		return line
	
def em(line):
    """ checks if a line contains *, and if so it turns them into the emphasize tags"""
    count = 0
    #replaces * with the correct tags for markdown
    for char in line:
        if "*" in line and count % 2 == 0:
            line = line.replace("*","<em>",1)
            count += 1
        if "*" in line and count%2 != 0:
            line = line.replace("*","</em>",1)
            count += 1
    return line

def link(line):
    """converts a line that contains a link and tag to the correct format containing tags"""
    link = []
    text = []
    open_brackets = []
    closed_brackets = []
    open_parenthesis = []
    closed_parenthesis = []
    new_line = line[:]
    complete_line = []
    i = 0
    count = 0
    #appends the locations of the [ ] and ( ) to lists of their index
    while i < len(line):
        if line[i] == "[":
            open_brackets.append(i)
        if line[i] == "]":
            closed_brackets.append(i)
        if line[i] =="(":
            open_parenthesis.append(i)
        if line[i] == ")":
            closed_parenthesis.append(i)
        i += 1
    k = 0
    while k < len(open_brackets):
        text.append(line[open_brackets[k]:closed_brackets[k]+1])
        k+=1
    j = 0
    while j < len(open_parenthesis):
      link.append(line[open_parenthesis[j]:closed_parenthesis[j]+1])
      j += 1
    l = 0
    left_off = 0
    #appends all the components of the original list into a new list that switches the location of the tag and link
    while l < len(open_brackets):
        complete_line.append(line[left_off:open_brackets[l]])
        complete_line.append(link[l])
        complete_line.append(text[l])
        left_off = closed_parenthesis[l]+1
        l += 1
    complete_line.append(line[left_off:])
    complete_line = "".join(complete_line)
    #changes the [ ] and ( ) to the correct format (markdown)
    for char in complete_line:
        if char in ["["]:
            complete_line = complete_line.replace("[","",1)
        if char in ["]"]:
            complete_line = complete_line.replace("]","</a>",1)
        if char in ["("]:
            complete_line = complete_line.replace("(",'<a href="',1)
        if char in [")"]:
            complete_line = complete_line.replace(")",'">')
    return complete_line

def convert(input_file,output_file):
	""" takes an input file and converts it to html format"""
	read_file = open(input_file, "rU")
	lines = read_file.readlines()
	write_file = open(output_file, "w")
	html = ""
	#runs through each line and checks if there are special characters in it and then calls functions accordingly
	for line in lines:
		if line.split(" ")[0] == "#" or line.split(" ")[0] == "##":
			html += header(line) + "\n"
		else:
			if line == "":
				return
			linepart = line
			linepart = paragraph(line)
			if "](" in line:
				linepart = link(linepart)
			linepart = em(linepart)		
			html += linepart + "\n"
	#writes the html string to a new file
	write_file.write(html)
	write_file.close()
	read_file.close()

 
