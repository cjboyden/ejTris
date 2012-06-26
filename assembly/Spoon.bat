@echo off
setlocal 

cd /D %~dp0

REM **************************************************
REM ** Set console window properties                **
REM **************************************************
TITLE ejTris console
REM COLOR F0

:: **************************************************
:: ** ejTris home                                  **
:: **************************************************

if "%EJTRIS_DIR%"=="" set EJTRIS_DIR=%~dp0
if %EJTRIS_DIR:~-1%==\ set EJTRIS_DIR=%EJTRIS_DIR:~0,-1%

cd %EJTRIS_DIR%

set EJTRIS_JAVA=javaw
set IS64BITJAVA=0

REM **************************************************
REM   Platform Specific native libraries            **
REM **************************************************

REM The following line is predicated on the 64-bit Sun
REM java output from -version which
REM looks like this (at the time of this writing):
REM
REM java version "1.6.0_17"
REM Java(TM) SE Runtime Environment (build 1.6.0_17-b04)
REM Java HotSpot(TM) 64-Bit Server VM (build 14.3-b01, mixed mode)
REM
REM Below is a logic to find the directory where java can found. We will
REM temporarily change the directory to that folder where we can run java there

:USEJAVAFROMPATH
FOR /F %%a IN ('java -version 2^>^&1^|find /C "64-Bit"') DO (SET /a IS64BITJAVA=%%a)
GOTO CHECK32VS64BITJAVA
:CHECK32VS64BITJAVA


IF %IS64BITJAVA% == 1 GOTO :USE64

:USE32
REM ===========================================
REM Using 32bit Java, so include 32bit native libs
REM ===========================================
set NATIVELIBSPATH=libext\native\win32
GOTO :CONTINUE
:USE64
REM ===========================================
REM Using 64bit java, so include 64bit native libs
REM ===========================================
set NATIVELIBSPATH=libext\native\win64
:CONTINUE
popd

REM **********************
REM   Collect arguments
REM **********************

set _cmdline=
:TopArg
if %1!==! goto EndArg
set _cmdline=%_cmdline% %1
shift
goto TopArg
:EndArg

REM ******************************************************************
REM ** Set java runtime options                                     **
REM ******************************************************************

set OPT=-Xmx512m -XX:MaxPermSize=256m -Djava.library.path=%NATIVELIBSPATH%
set OPT=%OPT% -classpath lib\*;libext\*
REM ***************
REM ** Run...    **
REM ***************

@echo on
start "ejTris" "%EJTRIS_JAVA%" %OPT% com.ewerp.ejtris.EJTris %_cmdline%