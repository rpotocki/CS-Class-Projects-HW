#My Name: Ryan Potocki
import random

def create_employee():
    """creates an employee based on a series of random selections/ints"""
    employee = {}
    t_or_f = [True, False]
    employee['favored'] = random.choice(t_or_f)
    if employee['favored'] == True:
        employee['score'] = random.randint(1,100)
    else:
        employee['score'] = random.randint(0,99)
    return employee

def create_company(sizes):
  """ this function takes a list of levels and makes a new list containing lists of dictionaries of employees for each level """
  company = []
  company_levels = sizes
  for level in company_levels:
    employeenum = range(level)
    temp_list= []
    for employee in employeenum:
      employee = create_employee()
      temp_list.append(employee)
    company.append(temp_list)
  return company

def get_pct_favored(company,level):
    """ this function determines how many employees in a certain level are favored """
    clist = company[level]
    count = 0
    for element in clist:
        if element['favored'] == True:
            count += 1
    return float(count)/len(clist)

def turnover(company,sizes,pct):
    pass


def main(num_simulations,sizes,pct):
    """ runs a simulation that gets the average of favored employees at each level for the whole simulation """
    total_simulation = []
    num_sims = range(num_simulations)
    #loops through each simulation
    for num in num_sims:
        new_company = []
        #creates a company for each simulation
        company = create_company(sizes)
        company_size = len(company)
        company_size = range(company_size)
        #loops through each level in company sizes
        for level in company_size:
            new_company.append([])
      #find the average favored employee for each level and append it to a list corresponding with that level
        for level in company_size:
            lvl_avg = get_pct_favored(company,level)
            new_company[level].append(lvl_avg)
        total_simulation.append(new_company)
    print "After", num_simulations,"trials, here are the average distribution of employees:"
    print
    print
    print "          favored non-favored"
    #prints out the averages of each list of level for the simulations
    for element in total_simulation:
        leveel = range(len((element)))
        print "Level", leveel,"  ",(sum(element)/len(element)),"  ",(1-(sum(element)/len(element)))
