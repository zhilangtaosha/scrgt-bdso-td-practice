import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'replaceSpace'})
export class ReplaceSpacePipe implements PipeTransform {
  transform(str: string, replaceChar: string): string {
    return str.replace(/ /g, replaceChar);
  }
}
