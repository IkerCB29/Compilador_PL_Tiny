especiales = ["E0","RE0", "E1", "RE1","E2","R1E2","R2E2","E3","RE3","E4",
					"RE4","E5","E6","RE6","E7","OP1","OP4","OP5","OP6"]

def add_words(words, output):
	for word in words:
		print(word)
		if len(word) == 1:
			if word == "ε":
				output.write("{} ")
			else:
				output.write("\"" + word + "\" ")
		elif word[0].isupper() and not word in especiales:
			output.write("<" + word + "> ")
		else:
			output.write( word + "() ")

def append_prod(words, output):
	# Append the production
	output.write("| ")
	add_words(words[2:], output)

def add_rule(words, output):
	output.write("void " + words[0] + "(): {} { ")
	add_words(words[2:], output)

def main():
	# Open the file and read the contents
	with open("gramatica_acondicionada.txt", "r") as file:
		contents = file.read()
	output = open("script_output", "w")

	# Split the contents into lines
	lines = contents.split("\n")
	last_token = None
	for line in lines:
		words = line.split(" ")
		if(len(words) < 3):
			continue
		
		if (last_token != None and last_token != words[0]):
			output.write("}\n")

		if(last_token != None and last_token == words[0]):
			append_prod(words, output)
		else:
			add_rule(words, output)
			last_token = words[0]


		print(last_token)

	# Create a new


if __name__ == "__main__":
	main()