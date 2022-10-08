import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'courrierstate'
})
export class CourrierstatePipe implements PipeTransform {

  transform(value: any, args?: any): string {
    switch (value) {
      case "ENVOYER" : return 'envoyé';
      case "REFUSER" : return 'refusé';
      case "TRAITER" : return 'traité';
      case "CLOTURER" : return 'cloturé';
      case "ENTRAITEMNT" : return 'en traitement';
      default: return value;
    }
  }
}
