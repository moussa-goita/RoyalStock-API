import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BonSortieFormPage } from './bon-sortie-form.page';

describe('BonSortieFormPage', () => {
  let component: BonSortieFormPage;
  let fixture: ComponentFixture<BonSortieFormPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(BonSortieFormPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
