JBoss, Home of Professional Open Source
Copyright 2006, Red Hat Middleware LLC, and individual contributors 
as indicated by the @author tags. 
See the copyright.txt in the distribution for a
full listing of individual contributors. 
This copyrighted material is made available to anyone wishing to use,
modify, copy, or redistribute it subject to the terms and conditions
of the GNU Lesser General Public License, v. 2.1.
This program is distributed in the hope that it will be useful, but WITHOUT A 
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
You should have received a copy of the GNU Lesser General Public License,
v.2.1 along with this distribution; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
MA  02110-1301, USA.

(C) 2005-2006,
@author JBoss Inc.
->	Configuration properties

The Ant properties that can be set (and their defaults and possible values)
are :

	Property				Values

	com.hp.mw.ts.properties			product.properties **
	(property file to load properties	(any filename)
	from) 

	com.hp.mw.ts.date			YYYY/MM/DD hh:mm **
	(date of build)				(any string)

	com.hp.mw.ts.installationdirectory	install **
	(installation directory)		(any directory) [1]

	com.hp.mw.ts.sourceid			unknown **
	(source identifier)			(any string)

	com.hp.mw.ts.version			unknown **
	(product version)			(any string)

	com.hp.mw.ts.builder			Hewlett Packard [user] (OS) **
	(product builder)			(any string)

	com.hp.mw.ts.notes			<empty> **
	(any notes)				(any string)

	com.hp.mw.ts.txoj.tests.compile	no **
	(compile tests)				yes

	com.hp.mw.ts.txoj.tests.install	no **
	(install tests)				yes

	com.hp.mw.ts.txoj.utilities.compile	yes **
	(compile utilities)			no

	com.hp.mw.ts.txoj.utilities.install	yes **
	(install utilities)			no


->	Build targets

The txoj 'build.xml' file contains the targets :

	Long name				Short name

	com.hp.mw.ts.txoj.compile		compile
	(compile java classes)

	com.hp.mw.ts.txoj.jar			jar **
	(generate module jar file)

	com.hp.mw.ts.txoj.install		install
	(install module)

	com.hp.mw.ts.txoj.clean		clean
	(clean generated files)


 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

  $Id: Readme.txt 2342 2006-03-30 13:06:17Z  $
