import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FournisseursFormPage } from './fournisseurs-form.page';

describe('FournisseursFormPage', () => {
  let component: FournisseursFormPage;
  let fixture: ComponentFixture<FournisseursFormPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(FournisseursFormPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
