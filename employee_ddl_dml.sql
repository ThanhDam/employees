USE [employees]
GO
/****** Object:  Table [dbo].[DEPARTMENT]    Script Date: 1/25/2021 4:35:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DEPARTMENT](
	[department_id] [nchar](10) NOT NULL,
	[department_name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_DEPARTMENT] PRIMARY KEY CLUSTERED 
(
	[department_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EMPLOYEE]    Script Date: 1/25/2021 4:35:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EMPLOYEE](
	[employee_id] [nchar](10) NOT NULL,
	[employee_last_name] [nvarchar](50) NOT NULL,
	[employee_first_name] [nvarchar](50) NOT NULL,
	[role_id] [nchar](2) NOT NULL,
	[date_of_birth] [date] NULL,
 CONSTRAINT [PK_EMPLOYEEE] PRIMARY KEY CLUSTERED 
(
	[employee_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ROLETBL]    Script Date: 1/25/2021 4:35:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ROLETBL](
	[role_id] [nchar](2) NOT NULL,
	[role_name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_ROLE] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TEAM]    Script Date: 1/25/2021 4:35:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TEAM](
	[team_id] [nchar](5) NOT NULL,
	[team_name] [nvarchar](50) NOT NULL,
	[department_id] [nchar](10) NOT NULL,
 CONSTRAINT [PK_TEAM] PRIMARY KEY CLUSTERED 
(
	[team_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TEAM_MEMBER_MANAGEMENT]    Script Date: 1/25/2021 4:35:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TEAM_MEMBER_MANAGEMENT](
	[team_id] [nchar](5) NOT NULL,
	[employee_id] [nchar](10) NOT NULL,
 CONSTRAINT [PK_TEAM_MEMBER_MANAGEMENT] PRIMARY KEY CLUSTERED 
(
	[team_id] ASC,
	[employee_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[DEPARTMENT] ([department_id], [department_name]) VALUES (N'BANK      ', N'Banking')
INSERT [dbo].[DEPARTMENT] ([department_id], [department_name]) VALUES (N'ECOMMER   ', N'E-commerce')
INSERT [dbo].[DEPARTMENT] ([department_id], [department_name]) VALUES (N'LIFINSUR  ', N'Life insurance')
INSERT [dbo].[DEPARTMENT] ([department_id], [department_name]) VALUES (N'LOGISTICS ', N'Logistic')
INSERT [dbo].[DEPARTMENT] ([department_id], [department_name]) VALUES (N'PNCINSUR  ', N'PnC insurance')
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000001', N'Nguyen', N'A', N'01', CAST(N'1970-02-03' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000002', N'Nguyen', N'B', N'02', CAST(N'1988-06-03' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000003', N'Nguyen', N'C', N'02', CAST(N'1990-05-06' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000004', N'Nguyen', N'D', N'03', CAST(N'1977-05-08' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000005', N'Nguyen', N'E', N'03', CAST(N'1990-10-25' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000006', N'Le', N'F', N'03', CAST(N'1972-06-08' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000007', N'Ly', N'G', N'04', CAST(N'1997-11-11' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000008', N'Ly', N'H', N'04', CAST(N'1991-01-25' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000009', N'Ly', N'K', N'04', CAST(N'1993-08-07' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000010', N'Nguyen', N'L', N'04', CAST(N'1993-09-08' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000011', N'Ly', N'M', N'04', CAST(N'1993-08-04' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000012', N'Le', N'N', N'04', CAST(N'1994-12-04' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000013', N'Le', N'O', N'04', CAST(N'1988-05-06' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000014', N'Le', N'P', N'04', CAST(N'1985-06-01' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000015', N'Le', N'S', N'04', CAST(N'1987-11-09' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000016', N'Ly', N'X', N'03', CAST(N'1988-05-16' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000017', N'Tran', N'I', N'04', CAST(N'1989-12-05' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000018', N'Tran', N'J', N'02', CAST(N'1989-01-25' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000019', N'Nguyen', N'Z', N'04', CAST(N'1989-03-03' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000020', N'Nguyen ', N'Y', N'04', CAST(N'1992-06-07' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000021', N'Nguyen', N'R', N'04', CAST(N'1994-11-08' AS Date))
INSERT [dbo].[EMPLOYEE] ([employee_id], [employee_last_name], [employee_first_name], [role_id], [date_of_birth]) VALUES (N'5000000022', N'Nguyen', N'T', N'04', CAST(N'1996-04-15' AS Date))
INSERT [dbo].[ROLETBL] ([role_id], [role_name]) VALUES (N'01', N'Director')
INSERT [dbo].[ROLETBL] ([role_id], [role_name]) VALUES (N'02', N'Department Manager')
INSERT [dbo].[ROLETBL] ([role_id], [role_name]) VALUES (N'03', N'Team Lead')
INSERT [dbo].[ROLETBL] ([role_id], [role_name]) VALUES (N'04', N'Team Member')
INSERT [dbo].[TEAM] ([team_id], [team_name], [department_id]) VALUES (N'ACB  ', N'ACB', N'BANK      ')
INSERT [dbo].[TEAM] ([team_id], [team_name], [department_id]) VALUES (N'BRI  ', N'Life Insurance Indonesia', N'LIFINSUR  ')
INSERT [dbo].[TEAM] ([team_id], [team_name], [department_id]) VALUES (N'PUM  ', N'Puma Malaysia', N'ECOMMER   ')
INSERT [dbo].[TEAM] ([team_id], [team_name], [department_id]) VALUES (N'PUS  ', N'Puma Singapore', N'ECOMMER   ')
INSERT [dbo].[TEAM] ([team_id], [team_name], [department_id]) VALUES (N'SIT  ', N'Sompo Insurance Thailand', N'PNCINSUR  ')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'ACB  ', N'5000000014')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'ACB  ', N'5000000015')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'ACB  ', N'5000000016')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'BRI  ', N'5000000002')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'BRI  ', N'5000000006')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'BRI  ', N'5000000008')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'BRI  ', N'5000000011')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'BRI  ', N'5000000012')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'BRI  ', N'5000000013')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'PUM  ', N'5000000003')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'PUM  ', N'5000000005')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'PUM  ', N'5000000009')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'PUS  ', N'5000000003')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'PUS  ', N'5000000005')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'PUS  ', N'5000000010')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'SIT  ', N'5000000002')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'SIT  ', N'5000000004')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'SIT  ', N'5000000007')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'SIT  ', N'5000000008')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'SIT  ', N'5000000019')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'SIT  ', N'5000000020')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'SIT  ', N'5000000021')
INSERT [dbo].[TEAM_MEMBER_MANAGEMENT] ([team_id], [employee_id]) VALUES (N'SIT  ', N'5000000022')
ALTER TABLE [dbo].[EMPLOYEE]  WITH CHECK ADD  CONSTRAINT [FK_EMPLOYEE_ROLE] FOREIGN KEY([role_id])
REFERENCES [dbo].[ROLETBL] ([role_id])
GO
ALTER TABLE [dbo].[EMPLOYEE] CHECK CONSTRAINT [FK_EMPLOYEE_ROLE]
GO
ALTER TABLE [dbo].[TEAM]  WITH CHECK ADD  CONSTRAINT [FK_Team_Deparment] FOREIGN KEY([department_id])
REFERENCES [dbo].[DEPARTMENT] ([department_id])
GO
ALTER TABLE [dbo].[TEAM] CHECK CONSTRAINT [FK_Team_Deparment]
GO
ALTER TABLE [dbo].[TEAM_MEMBER_MANAGEMENT]  WITH CHECK ADD  CONSTRAINT [FK_TEAM_EMPLOYEE_1] FOREIGN KEY([team_id])
REFERENCES [dbo].[TEAM] ([team_id])
GO
ALTER TABLE [dbo].[TEAM_MEMBER_MANAGEMENT] CHECK CONSTRAINT [FK_TEAM_EMPLOYEE_1]
GO
ALTER TABLE [dbo].[TEAM_MEMBER_MANAGEMENT]  WITH CHECK ADD  CONSTRAINT [FK_TEAM_EMPLOYEE_2] FOREIGN KEY([employee_id])
REFERENCES [dbo].[EMPLOYEE] ([employee_id])
GO
ALTER TABLE [dbo].[TEAM_MEMBER_MANAGEMENT] CHECK CONSTRAINT [FK_TEAM_EMPLOYEE_2]
GO
