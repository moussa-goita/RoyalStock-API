import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EspaceFournisseurPage } from './espace-fournisseur.page';

describe('EspaceFournisseurPage', () => {
  let component: EspaceFournisseurPage;
  let fixture: ComponentFixture<EspaceFournisseurPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(EspaceFournisseurPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
