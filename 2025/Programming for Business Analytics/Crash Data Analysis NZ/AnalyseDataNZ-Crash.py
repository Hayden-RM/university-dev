###################################################################
##### Student Name: Hayden Richard-Marsters
##### ID: 21152003
#####################################################################

# Import relevant packages
import pandas as pd

"""
# How to select 1.2.1. a subsetting of a dataframe using several conditions.
 
df_example = pd.read_csv("Employees.csv")
print('Full dataframe:\n',df_example)
df_example1 = df_example[ (df_example['Gender']=='M') & (df_example['Age']>40) ]
print('Male employees above 40:\n', df_example1)
df_example2 = df_example[ (df_example['Height']<1.74) | (df_example['Age']>40) ]
print('Employees above 40 or height below 1.74:\n', df_example2)


print('Here I write a comment.')
"""
#####################################################################
# Preamble
#####################################################################

# 0. Load crash_analysis dataframe
df = pd.read_csv("Crash_Analysis_System_(CAS)_data_sample.csv")
print("Head of dataframe: ", df.head())
print("Dataframe shape: ", df.shape)
print("Names of columns: ", df.columns.tolist())

    
#####################################################################
# Question1         
#####################################################################
# code to perform question1
print('\n#######################################################################\n\
              Question 1                                                      \n\
#######################################################################')
# create dataframe subsets
df1 = df[df['speedLimit'] > 50]
df2 = df[df['speedLimit'] <= 50]
df3 = df[(df['speedLimit'] > 50) & (df['crashSeverity'] == 'Fatal Crash')]
df4 = df[(df['speedLimit'] <= 50) & (df['crashSeverity'] == 'Fatal Crash')]

# compute ratios guarding against division by zero
ratio1 = len(df1) / len(df3) if len(df3) > 0 else float('nan')
ratio2 = len(df2) / len(df4) if len(df4) > 0 else float('nan')

# Print ratios
print('\nRatio of df1 to df3 (speedLimit > 50 / > 50 & Fatal): ', round(ratio1, 2))
print('Ratio of df2 to df4 (speedLimit <= 50 / <= 50 & Fatal): ', round(ratio2, 2))

# Analysis 
print('\nImpact of the speed limit on the fatality rate:')
print('\n- In the > 50km/h limit zone, 1 fatal crash occurs in every 56.54 crashes - 1.77% fatal-crash rate. ' \
'In the <= 50km/h limit zone, 1 fatal crash occurs in every 253.05 crashes - 0.4% fatal-crash rate. Upon first glance, ' \
'the data suggests that crashes within > 50km/h zones are significantly more likely to be fatal compared to crashes within <= 50km/h zones')
print('\n- Furthermore by relative risk, crashes within a speedzone with limit above 50km/h are 4.425x (1.77%/0.4%) as likely' \
' to be a fatal-crash than a crash that occurs within a speedzone with a limit at or below 50km/h.')
print('\n- Therefore, we can conclude from the ratio and relative risk analysis that zones with higher speedlimits drastically increase the liklihood of a fatal crash, and that' \
' speed does have an impact on the severity of the crash.')


#####################################################################
# Question2         
#####################################################################
# code to perform question2
print('\n#######################################################################\n\
              Question 2                                                      \n\
#######################################################################')
# Create dataframe subsets
df1 = df[df['crashSHDescription'] == 'Yes']
df2 = df[df['crashSHDescription'] == 'No']
df3 = df[(df['crashSHDescription'] == 'Yes') & (df['crashSeverity'] == 'Fatal Crash')]
df4 = df[(df['crashSHDescription'] == 'No') & (df['crashSeverity'] == 'Fatal Crash')]

# compute ratios guarding against division by zero
ratio1 = len(df1) / len(df3) if len(df3) > 0 else float('nan')
ratio2 = len(df2) / len(df4) if len(df4) > 0 else float('nan')

# Print ratios
print('\nRatio of df1 to df3 (SH = Yes / SH = Yes & Fatal): ', round(ratio1, 2))
print('Ratio of df2 to df4 (SH = No / SH= No & Fatal): ', round(ratio2, 2))

# Analysis
print('\nImpact of the type of road:')
print('\n- On state-highway (SH) roads, 1 fatal crash occurs every 61.08 crashes - fatal-crash rate of 1.64%. ' \
'On non-state-highway (non-SH) roads, 1 fatal crash occurs every 138 crashes - fatal-crash rate of 0.72%.')
print('\n- Furthermore by relative risk, crashes occurring on SH type roads are 2.28x (1.64%/0.72%) more likely to be fatal than' \
' on non-SH type roads.')
print('\n- Therefore, the data suggests that SH type roads do increase the severity of the crash, ' \
'implied by the higher fatal-crash rate (1.64% > 0.72%) and ratio of 1 in 61.08 compared to 1 in 138 to be fatal in non-SH crashes. ') 
print('\n- A key contributing difference to higher fatality rates on SH type roads may be ' \
'attributed to the have higher speed limits of SH type roads, as suggested in Q.1., ' \
'i.e. higher speedlimits equals greater liklihood of a fatal crash.')



#####################################################################
# Question3         
#####################################################################
# code to perform question3
print('\n#######################################################################\n\
              Question 3                                                      \n\
#######################################################################')
# Create dataframe subsets
df1 = df[df['weatherA'] == 'Heavy rain']
df2 = df[df['weatherA'] == 'Fine']
df3 = df[(df['weatherA'] == 'Heavy rain') & (df['crashSeverity'] == 'Fatal Crash')]
df4 = df[(df['weatherA'] == 'Fine') & (df['crashSeverity'] == 'Fatal Crash')]

# compute ratios guarding against division by zero
ratio1 = len(df1) / len(df3) if len(df3) > 0 else float('nan')
ratio2 = len(df2) / len(df4) if len(df4) > 0 else float('nan')

# Print ratios
print('\nRatio of df1 to df3 (Heavy rain / Heavy rain & fatal): ', round(ratio1, 2))
print('Ratio of df2 to df4 (Fine / Fine & Fatal): ', round(ratio2, 2))

print('\nImpact of weather condition:')
print('\n- Upon first glance - both ratios are very similiar, which initially implies that the weather has minimal impact on severity of a crash' \
'however, the data does imply that heavy rain does increase crash severity slighty with 1 in 91.43 crashes being fatal, or' \
' a fatality rate of 1.09%; compared to fine weather, which 1 every 93.8 crashes are fatal, implies a relatively ' \
'lower fatality rate of 1.07%.')
print('\n- The difference between ratios is very small, about a 2.6% relative increase in fatality risk when it\'s heavily raining. ' \
'Therefore, at this stage, the data suggests that weather has minimal impact on severity of a crash, implied by the minimal difference in fatality rates between the two groups.')
print('\nNB: With such small differences upon first glance, a statistical significance test should be applied. As it is generally assumed that, ' \
'worsened weather conditions i.e. heavier rain, leads to more dangerous driving conditions and therefore a higher fatality rate ' \
'among crashes in Heavy Rain relative to crashes in Fine weather conditions. ')


    
#####################################################################
# Question4         
#####################################################################
# code to perform question4
print('\n#######################################################################\n\
              Question 4                                                      \n\
#######################################################################')
# Create dataframe subsets
df1 = df[df['light'] == 'Dark']
df2 = df[df['light'] == 'Bright sun']
df3 = df[(df['light'] == 'Dark') & (df['crashSeverity'] == 'Fatal Crash')]
df4 = df[(df['light'] == 'Bright sun') & (df['crashSeverity'] == 'Fatal Crash')]

# Compute ratios guarding against division by zero
ratio1 = len(df1) / len(df3) if len(df3) > 0 else float('nan')
ratio2 = len(df2) / len(df4) if len(df4) > 0 else float('nan')
 
# Print ratios 
print('\nRatio of df1 to df3 (Dark / Dark & fatal): ', round(ratio1, 2))
print('Ratio of df2 to df4 (Bright sun / Bright sun & Fatal): ', round(ratio2, 2))

# Print analysis
print('\nImpact of the light condition:')
print('\n- In dark conditions, 1 in every 83.79 crashes are fatal, or a fatality rate of 1.19%; compared to in Bright sun, 1 in' \
' every 101 crashes are fatal, or a fatality rate of 0.99%. Immediately, we can see that crashes in the dark are more likely to be fatal' \
' than crashes that occur in bright sun')
print('\n- Furthermore by relative risk, crashes that occur in the dark are 1.2x (1.19/0.99) more likely to be fatal; or carry a roughly 20% ((1.19-0.99)/0.99)' \
' increase in relative risk')
print('\n- Therefore, we conclude that with a 20% increase in relative fatality risk when a crash occurs in dark compared to bright-sun' \
' - that crashes that occur in dark conditions are more likely to be fatal.')
print('\nNB: While a 20% relative increase sounds substantial, the absolute difference is only' \
' 0.20 percentage points, thus a test should be applied to test whether this difference is statistically significant')



    
#####################################################################
# Question5         
#####################################################################
# code to perform question5
print('\n#######################################################################\n\
              Question 5                                                      \n\
#######################################################################')
# Create dataframe subsets
df1 = df[df['flatHill'] == 'Hill Road']
df2 = df[df['flatHill'] == 'Flat']
df3 = df[(df['flatHill'] == 'Hill Road') & (df['crashSeverity'] == 'Serious Crash')]
df4 = df[(df['flatHill'] == 'Flat') & (df['crashSeverity'] == 'Serious Crash')]

# Compute ratios guarding against division by zero
ratio1 = len(df1) / len(df3) if len(df3) > 0 else float('nan')
ratio2 = len(df2) / len(df4) if len(df4) > 0 else float('nan')

# Print ratios 
print('\nRatio of df1 to df3 (Hill / Hill & Serious crash): ', round(ratio1, 2))
print('Ratio of df2 to df4 (Flat / Flat & Serious crash): ', round(ratio2, 2))

# Print analysis
print('\nImpact of surface condition:')
print('\n- 1 in every 12.56 crashes that occur on a hill road are Serious Crashes, implying a serious-crash rate of 8.22%; ' \
'On the other hand, 1 in 15.11 crashes that occur on a flat hill are Serious Crashes, implying a serious-crash rate of 6.62%. ' \
'From this we can see immediately see that, that a crash that occurs on a Hill Road is more likely to be termed as a Serious Crash.')
print('\n- Furthermore by relative risk, the data suggests that crashes that occur on a hill are ' \
'1.24x (8.22% / 6.22%) more likely to be a serious crash than a crash on a flat road, ' \
'or that crashes on hills carry a  32% ((8.22-6.22)/6.22) greater relative risk of a serious crash than crashes that occur on flat road.')
print('\n- Therefore, based on the ratio and relative risk analysis, that the data suggests that crashes' \
' on hill roads are more likely to be serious crashes than crashes that occur on flat roads, but that overall the angle of the road' \
' (Flat or Hill road) has minimal impact on the severity of the crash.')
print('\nNB: A statistical significance test should be applied to test the claim that the difference is statistically minor')




#####################################################################
# Question6         
#####################################################################
# code to perform question6
print('\n#######################################################################\n\
              Question 6                                                      \n\
#######################################################################')
# Create dataframe subsets
df1 = df[df['motorcycle'] == 1]
df2 = df[df['motorcycle'] == 0]
df3 = df[(df['motorcycle'] == 1) & (df['crashSeverity'] == 'Fatal Crash')]
df4 = df[(df['motorcycle'] == 0) & (df['crashSeverity'] == 'Fatal Crash')]

# Compute ratios guarding against division by zero
ratio1 = len(df1) / len(df3) if len(df3) > 0 else float('nan')
ratio2 = len(df2) / len(df4) if len(df4) > 0 else float('nan')

# Print ratios 
print('\nRatio of df1 to df3 (motorcycle(1) / motorcycle(1) & Fatal crash): ', round(ratio1, 2))
print('Ratio of df2 to df4 (motorcycle(0) / motorcycle(0) & Fatal crash): ', round(ratio2, 2))

# Print analysis
print('\nInfluence of the vehicle type:')
print('\n- According to the data, 1 in every 27.15 motorcycle crashes are fatal, implying a 3.68% fatality rate; ' \
'compared to non-motorcycle crashes, 1 in every 117.21 crashes are fatal, implying a fatality rate of 0.85%.' \
' From this, we can immediately see that among crashes that occured, a crash involving motorcycles is more likely to be fatal than a crash' \
' invovling non-motorcycles.')
print('\n- Furthermore by relative risk, the data suggests that crashes involving motorcycles are 4.33x (3.68/0.85) more likely to be fatal than compared ' \
'to crashes involving non-motorcycles, or that crashes that involve motorcycles carry' \
' a ~330% ((3.68-0.85)/0.85) increase in relative fatality risk.')
print('\n- Therefore, based on the ratio analysis and relative risk analysis, that we can confidently conclude that ' \
'crashes that involve motorcycles are inherently more dangerous as they are significantly more likely to be fatal than crashes involving non-motorcycle vehicles.')


#####################################################################
# Question7         
#####################################################################
# code to perform question7
print('\n#######################################################################\n\
              Question 7                                                      \n\
#######################################################################')
# Create dataframe subsets
df1 = df[df['motorcycle'] == 1]
df2 = df[df['motorcycle'] == 0]
df3 = df[(df['motorcycle'] == 1) & ((df['crashSeverity'] == 'Fatal Crash') | (df['crashSeverity'] == 'Serious Crash'))]
df4 = df[(df['motorcycle'] == 0) & ((df['crashSeverity'] == 'Fatal Crash') | (df['crashSeverity'] == 'Serious Crash'))]

# Compute ratios guarding against division by zero
ratio1 = len(df1) / len(df3) if len(df3) > 0 else float('nan')
ratio2 = len(df2) / len(df4) if len(df4) > 0 else float('nan')

# Print ratios 
print('\nRatio of df1 to df3 (motorcycle(1) / motorcycle(1) & (Fatal Crash or Serious Crash): ', round(ratio1, 2))
print('Ratio of df2 to df4 (motorcycle(0) / motorcycle(0) & (Fatal Crash or Serious Crash): ', round(ratio2, 2))

# Print analysis
print('\nImpact of vehicle type:')
print('\n- 1 in 2.77 crashes that occur invloving motorcycles are serious or fatal (36% serious-or-fatal rate); ' \
'On the other hand, 1 in 15.62 crashes that occur involving non-motorcycles are serious or fatal (6.4% serious-or-fatal rate). ' \
'Immediately we can see that, the data suggests that crashes that involve motorcycles are drastically more likely to be serious or fatal than crashes involving non-motorcyles.')
print('\n- Furthermore by relative risk, crashes involving motorcyclists are 5.63x (36%/6.4%) as likely to be serious or fatal' \
' or fatal compared to crashes involving non-motorcyclists - a 463% ((36%-6.4%)/6.4%) greater carry in relative risk compared to crashes involving non-motorcycles.')
print('Therefore, we can conclude that crashes involving motorcyclists are significantly more likely to be serious or fatal compared ' \
' to crashes involving non-motorcyclists - suggesting that the type of vehicle (motorcycle or not) does impact the severity of a crash.')


#####################################################################
# Question8         
#####################################################################
# code to perform question8
print('\n#######################################################################\n\
              Question 8                                                      \n\
#######################################################################')
# Create dataframe subsets
df1 = df[df['roadSurface'] == 'Unsealed']
df2 = df[df['roadSurface'] == 'Sealed']
df3 = df[(df['roadSurface'] == 'Unsealed') & ((df['crashSeverity'] == 'Fatal Crash') | (df['crashSeverity'] == 'Serious Crash'))]
df4 = df[(df['roadSurface'] == 'Sealed') & ((df['crashSeverity'] == 'Fatal Crash') | (df['crashSeverity'] == 'Serious Crash'))]

# Compute ratios guarding against division by zero
ratio1 = len(df1) / len(df3) if len(df3) > 0 else float('nan')
ratio2 = len(df2) / len(df4) if len(df4) > 0 else float('nan')

# Print ratios 
print('\nRatio of df1 to df3 (Unsealed / Unsealed & (Fatal Crash or Serious Crash): ', round(ratio1, 2))
print('Ratio of df2 to df4 (Sealed / Sealed & (Fatal Crash or Serious Crash): ', round(ratio2, 2))


print('\nImpact of surface condition:')
print('\n- 1 in 6.6 crashes that occur on Unsealed roads are deemed as Serious or Fatal (15% Serious-or-Fatal rate); ' \
' On the other hand, 1 in 13.05 crashes that occur on Sealed roads are deemed as Serious or Fatal (7.7% Serious-or-Fatal rate).' \
' From this, we can immediately see that crashes that occur on Unsealed roads are more likely to be Serious or Fatal than' \
' crashes that occur on Sealed roads. ')
print('\n- Further more by relative risk, crashes on Unsealed are about 1.94x (15%/7.7%) as likely to be Serious or Fatal comapred to crashes on sealed surfaces - ' \
' a 94% ((15%-7.7%)/7.7%) greater carry in relative risk compared to crashes on sealed roads. ')
print('\n- Therefore, we can conclude that drivers crashing on Unsealed roads face almost double the probability of a serious or fatal crash compared to drivers who crash ' \
'on Sealed roads - suggesting that Unsealed roads are inherently more dangerous, and that the type of road surface (sealed/unsealed) does impact the severity of a crash. ')

print('\nAssumption: Every Fatal Crash is also classified as Serious, but not every Serious Crash is Fatal')