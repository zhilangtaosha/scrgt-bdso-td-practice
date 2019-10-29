#!/usr/bin/python

import argparse
import subprocess
import sys

parser = argparse.ArgumentParser(description="Use this script to create or destroy the BDSO infrastructure")
parser.add_argument('command', choices=['create', 'destroy'], help='Include create or destroy to create or destroy the infrastructure')
args = parser.parse_args()

if len(sys.argv) < 1:
  parser.print_help()
  sys.exit(1)

if args.command == 'create':
    subprocess.call(['./start.sh'])
elif args.command == 'destroy':
    subprocess.call(['./destroy.sh'])
